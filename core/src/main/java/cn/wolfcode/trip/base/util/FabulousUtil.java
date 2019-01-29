package cn.wolfcode.trip.base.util;

import cn.wolfcode.trip.base.domain.User;

import java.util.List;

public class FabulousUtil {
    public static String getFabulousNames(List<User> list){
        String names = "";
        if (list.size()<=6){
            for (int i = 0;i<list.size();i++){
                if (i!=list.size()-1){
                    names=names+list.get(i).getNickName()+",";
                }else {
                    names=names+list.get(i).getNickName();
                }
            }
        }else {
            for (int i = 0;i<6;i++){
                if (i!=list.size()-1){
                    names=names+list.get(i).getNickName()+",";
                }else {
                    names=names+list.get(i).getNickName();
                }
            }
        }
        return names;
    }
}
