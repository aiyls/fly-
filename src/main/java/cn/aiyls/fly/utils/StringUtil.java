package cn.aiyls.fly.utils;

import cn.aiyls.fly.enums.ReturnCodes;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: aiyls
 * @CreateTime: 2021/6/6
 * @Desc:
 */
public class StringUtil {
    public static String yuanToFen(String amount) throws ParseException {
        NumberFormat format = NumberFormat.getInstance();
        Number number = format.parse(amount);
        double temp = number.doubleValue() * 100.0;
        format.setGroupingUsed(false);
        // 设置返回数的小数部分所允许的最大位数
        format.setMaximumFractionDigits(0);
        amount = format.format(temp);
        return amount;
    }

    public static String fenToYuan(String amount) throws ParseException{
        NumberFormat format = NumberFormat.getInstance();
        Number number = format.parse(amount);
        double temp = number.doubleValue() / 100.0;
        format.setGroupingUsed(false);
        // 设置返回的小数部分所允许的最大位数
        format.setMaximumFractionDigits(2);
        amount = format.format(temp);
        return amount;
    }

    public static String listSpitByString(Object[] array,String separator) {
        String string = StringUtils.join(array, separator);
        return string;
    }

    public static String judgeStrIsEmpty(String str) {
        if(StringUtils.isEmpty(str)) {
            return "";
        }else {
            return str;
        }
    }

    public static Result<Object> judgeStrIsEmpty(JSONObject json, String... key) {
        for (int i = 0; i < key.length; i++) {
            String getKey = key[i];
            if (org.springframework.util.StringUtils.isEmpty(json.getString(getKey))) {
                return new Result<Object>(3000,String.format("参数%s为空", getKey));
            }
        }
        return new Result<Object>(ReturnCodes.success);
    }

    public static String getNumByLen(Integer len) {
        String num = "";
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            num += random.nextInt(10);
        }
        return num;
    }

    /**
     * 字节数组转换成字符串
     * @param byteArray
     * @return
     */
    public static String byteArrayConvertStr(byte[] byteArray) {
        return new String(byteArray);
    }

    /**
     * 字符串转换成字节数组
     * @param string
     * @return
     */
    public static byte[] strConvertByteArray(String string) {
        return string.getBytes();
    }

    /**
     * 生成随机数字和字母,
     * @param length
     * @return
     */
    public static String getStringRandom(int length) {
        String val = "";
        Random random = new Random();
        //参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    /**
     * 判断字符串中/出现的位置
     * @param string
     * @param count
     * @return
     */
    public static int getCharacterPosition(String string,int count){
        //这里是获取"/"符号的位置
        Matcher slashMatcher = Pattern.compile("/").matcher(string);
        int mIdx = 0;
        while(slashMatcher.find()) {
            mIdx++;
            //当"/"符号第count出现的位置
            if(mIdx == count){
                break;
            }
        }
        return slashMatcher.start();
    }

    /**
     * 判断sign字符第count次出现的位置
     * @param sign
     * @param string
     * @param count
     * @return
     */
    public static int getCharacterPosition(String sign,String string,int count){
        //这里是获取"/"符号的位置
        Matcher slashMatcher = Pattern.compile(sign).matcher(string);
        int mIdx = 0;
        while(slashMatcher.find()) {
            mIdx++;
            //当"/"符号第count出现的位置
            if(mIdx == count){
                break;
            }
        }
        return slashMatcher.start();
    }

    public static String jsonKeyEdit(String jsonStr){

        //转换后的字符串
        String str = jsonStr;

        //满足json字符串key的正则
        String regx = "\"\\w+\":";

        //1.将正在表达式封装成对象Patten 类来实现
        Pattern pattern = Pattern.compile(regx);

        //2.将字符串和正则表达式相关联
        Matcher matcher = pattern.matcher(jsonStr);

        //查找符合规则的子串
        while(matcher.find()){

            //获取的字符串的首位置和末位置 以及 获取 字符串   在哪里？什么疯转的方法
            // System.out.println(matcher.start()+"--"+matcher.end()+" : "+matcher.group());
            //输出一下信息
            //1--10 : "status":
            //12--19 : "code":
            //26--36 : "message":
            //41--48 : "data":
            //49--62 : "buy_status":
            //66--80 : "track_price":
            //84--101 : "track_buy_tips":
            //133--142 : "tracks":
            //144--158 : "is_purchase":
            //162--171 : "pinyin":
            //183--197 : "is_purchase":
            //201--210 : "pinyin":

            //取代下划线命名的key为驼峰命名的key
            //ToCamelCase类中camelCase方法将下划线命名转驼峰命名
            str = str.replaceFirst(matcher.group(),camelCase(matcher.group()));
        }
        System.err.println("转换后 = " +str);
        System.err.println("\n");
        return str;
    }

    //驼峰命名
    public static String camelCase(String str){
        String camelCase = "";
        String [] arr = str.split("_");
        List<String> list = new ArrayList<String>();

        //将数组中非空字符串添加至list
        for(String a : arr){
            if(a.length() > 0){
                list.add(a);
            }
        }
        for(int i=0;i<list.size();i++){
            if(i>0){    //后面单词首字母大写
                char c = list.get(i).charAt(0);
                String s = String.valueOf(c).toUpperCase() + list.get(i).substring(1).toLowerCase();
                camelCase+=s;
            }else{  //首个单词小写
                camelCase+=list.get(i).toLowerCase();
            }
        }
        return camelCase;
    }
}
