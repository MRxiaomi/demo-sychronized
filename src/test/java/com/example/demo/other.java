package com.example.demo;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liuyumeng on 2018/5/24.
 */
public class other {
    public static void main(String[] args) {
//        System.out.println("+："+((Long.valueOf(Integer.MAX_VALUE) << 32) + Integer.MAX_VALUE));
//        System.out.println("|："+((Long.valueOf(Integer.MAX_VALUE) << 32) |  Integer.MAX_VALUE));
        //getFolderById(1986497, 3);
        isAvatarPath();
    }


    /**
     * 类似hash算法，生成一定层级的目录
     * @param id
     * @param levels
     * @return
     */
    private static String getFolderById(int id, int levels) {
        if (levels <= 1) {
            levels = 2;
        }

        StringBuilder buff = new StringBuilder();
        for (int i = levels; i > 0; i--) {
            int move = (i << 3) - 4;
            buff.append(Long.toHexString((id & (0XF << move)) >> move));
            move -= 4;
            buff.append(Long.toHexString((id & (0XF << move)) >> move));
            if (i > 1) {
                buff.append(File.separator);
            }
        }

        return buff.toString();
    }


    private static final String PATTERN_AVATAR_PATH = "^(?:/[0-9a-fA-F]{2}){3}/\\d+/\\d+\\.(?:jpg|jpeg|gif|png])$";

    private static void isAvatarPath(){
        Pattern pattern = Pattern.compile(PATTERN_AVATAR_PATH);
        Matcher ma1 = pattern.matcher("/c7/cc/a6/13094054/17.jpg");
        if (ma1.matches()){
            System.out.println("匹配成功 - 1");
        }
        Matcher ma2 = pattern.matcher("/cc/a6/13094054/17.jpg");
        if (ma2.matches()){
            System.out.println("匹配成功 - 2");
        }
    }

    private static final String PATTERN_OLD_AVATAR_PREFIX = "img.dxycdn.com/avatars/(^\\d+$)/";
    private static final Pattern OLD_AVATAR_PREFIX_PATTERN = Pattern.compile(PATTERN_OLD_AVATAR_PREFIX);

//    private static String convertAvatarUrlSize(String url, int size) {
//        if (isOldAvatarPath(url)) {
//            Matcher matcher = OLD_AVATAR_PREFIX_PATTERN.matcher(url);
//            if (matcher.matches()) {
//                return matcher.replaceFirst(String.valueOf(size));
//            }
//        } else if (url.contains("!w")) {
//            String[] splits = url.split("!w");
//            if (ArrayUtil.isNotEmpty(splits) && splits.length == 2 && NumberUtil.is(splits[1])) {
//                return splits[0] + "!w" + size;
//            }
//        }
//        return null;
//    }
}
