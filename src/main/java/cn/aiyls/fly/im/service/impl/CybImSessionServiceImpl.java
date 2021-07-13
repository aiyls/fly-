package cn.aiyls.fly.im.service.impl;

import cn.aiyls.fly.entity.CybImSession;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.aiyls.fly.im.mapper.CybImMessageMapper;
import cn.aiyls.fly.im.mapper.CybImSessionMapper;
import cn.aiyls.fly.im.service.ICybImSessionService;
import cn.aiyls.fly.im.vo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * IM会话表 服务实现类
 * </p>
 *
 * @author gu.yuanlin@foundbyte.com
 * @since 2020-12-24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CybImSessionServiceImpl extends ServiceImpl<CybImSessionMapper, CybImSession> implements ICybImSessionService {

    @Resource
    private CybImSessionMapper sessionMapper;

    @Resource
    private CybImMessageMapper messageMapper;

    @Override
    public List<SessionVO> getSessionListByUserId(Long userId) {
        return sessionMapper.getSessionListByUserId(userId);
    }

    @Override
    public List<AvatarSessionVO> getAvatarSessionListByUserId(Long userId, String userName, String userPhone, String userRole) {
        // 用户角色：用户1；服务提供者2
        List<String> userRoleList = Arrays.asList("1", "2");
        if (!userRoleList.contains(userRole)) {
            throw new RuntimeException("用户角色类型错误，请检查");
        }
        List<SessionVO> originList = sessionMapper.getSessionListByUserId(userId);
        List<AvatarSessionVO> resultList = new ArrayList<>();

        originList.forEach(item -> {
            AvatarSessionVO avatarSessionVO = Convert.convert(AvatarSessionVO.class, item);
            avatarSessionVO.setUserName(userName);
            avatarSessionVO.setUserPhone(userPhone);
            if ("1".equals(userRole)) { // 当前是用户，用户的姓名，电话由token获取，头像在前端缓存，通过feign获取服务提供者的姓名，电话，头像等信息
//                Result<CybUserSupplier> result = supplierFeign.getUserSupplierById(item.getOppositeId());
//                if (Result.OK.equals(result.getCode())) {
//                    CybUserSupplier supplier = result.getData();
//                    avatarSessionVO.setOppositeName(supplier.getUserName());
//                    avatarSessionVO.setOppositePhone(supplier.getUserPhone());
//                    avatarSessionVO.setOppositeAvatar(supplier.getHeadPortrait());
//                } else {
//                    throw new RuntimeException("supplier feign error, please check");
//                }
            } else if ("2".equals(userRole)) { // 当前是服务提供者，服务提供者的姓名，电话由token获取，头像在前端缓存，通过feign获取用户的姓名，电话，头像等信息
//                Result<CybUserCustomer> result = customerFeign.getCustomerById(item.getOppositeId());
//                if (Result.OK.equals(result.getCode())) {
//                    CybUserCustomer customer = result.getData();
//                    avatarSessionVO.setOppositeName(customer.getUserName());
//                    avatarSessionVO.setOppositePhone(customer.getUserPhone());
//                    avatarSessionVO.setOppositeAvatar(customer.getHeadPortrait());
//                } else {
//                    throw new RuntimeException("customer feign error, please check");
//                }
            }
            resultList.add(avatarSessionVO);
        });
        return resultList;
    }

    @Override
    public List<CybImSession> checkSession(CheckSessionVO checkSessionVO) {
        List<CybImSession> list = sessionMapper.checkSession(checkSessionVO);
        int count = list.size();
        if (count > 0) {
            this.restoreSession(checkSessionVO);
        }
        return list;
    }

    @Override
    public CreateSuccessVO createSession(CreateSessionVO createSessionVO) {
        if (createSessionVO.getOperatingTime() == null) {
            createSessionVO.setOperatingTime(LocalDateTime.now());
        }

        List<CybImSession> list = this.checkSession(new CheckSessionVO(createSessionVO));
        int count = list.size();

        CreateSuccessVO createSuccessVO = new CreateSuccessVO();
        if (count > 0) {
            for (CybImSession item : list) {
                if (createSessionVO.getUserId().equals(item.getUserId()) && createSessionVO.getOppositeId().equals(item.getOppositeId())) {
                    createSuccessVO.setUuid(item.getUuid());
                    createSuccessVO.setId(item.getId());
                    createSuccessVO.setOppositePhone(item.getOppositePhone());
                    Long lastId = messageMapper.getLastIdByUuid(item.getUuid());
                    createSuccessVO.setLastId(lastId == null ? 0L : lastId);
                    break;
                }
            }
        } else {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            createSuccessVO.setUuid(uuid);
            createSuccessVO.setLastId(0L);

            CybImSession userSession = createSessionVO.createUserSession();
            userSession.setUuid(uuid);
            sessionMapper.insertSessionAndReturnId(userSession);
            createSuccessVO.setId(userSession.getId());
            createSuccessVO.setOppositePhone(userSession.getOppositePhone());

            CybImSession oppositeSession = createSessionVO.createOppositeSession();
            oppositeSession.setUuid(uuid);
            sessionMapper.insertSessionAndReturnId(oppositeSession);
        }
        return createSuccessVO;
    }

    @Override
    public void restoreSession(CheckSessionVO checkSessionVO) {
        // 如果是走的创建会话的会话检查，则会有两个用户的姓名，则需要更新两次
        // 如果是走的发消息的会话检查，只有uuid，则只更新一次
        sessionMapper.restoreSession(checkSessionVO);
        if (StrUtil.isNotBlank(checkSessionVO.getUserName()) && StrUtil.isNotBlank(checkSessionVO.getOppositeName())) {
            sessionMapper.restoreOppositeSession(checkSessionVO);
        }
    }

    @Override
    public void topSession(EditSessionVO editSessionVO) {
        if (editSessionVO.getOperatingTime() == null) {
            editSessionVO.setOperatingTime(LocalDateTime.now());
        }
        editSessionVO.setTopFlag("1");
        sessionMapper.changeTopSession(editSessionVO);
    }

    @Override
    public void cancelTopSession(EditSessionVO editSessionVO) {
        if (editSessionVO.getOperatingTime() == null) {
            editSessionVO.setOperatingTime(LocalDateTime.now());
        }
        editSessionVO.setTopFlag("0");
        sessionMapper.changeTopSession(editSessionVO);
    }

    @Override
    public void deleteSession(Long id) {
        EditSessionVO editSessionVO = new EditSessionVO();
        editSessionVO.setId(id);
        editSessionVO.setOperatingTime(LocalDateTime.now());
        sessionMapper.deleteSession(editSessionVO);
    }

}
