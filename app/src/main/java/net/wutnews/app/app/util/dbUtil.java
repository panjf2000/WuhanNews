package net.wutnews.app.app.util;

import java.util.List;

import net.tsz.afinal.FinalDb;

import android.content.Context;

/**
 * 数据库工具类 说明:增删改查 全部用实体类实现, 实体类里属性名为字段名, 并必须实现get和set方法, 实体类中一定更要有id字段,
 * 实体类名为表明,或在实体类类名上定义 @Table(name="Table_Name")则表名为Table_Name,
 * 一定要有空构造函数,否则查询会出错, 新建全属性构造函数,去掉其中一个属性名,则去掉的属性名为主键,并且自增,一般为id(防止出错)
 *
 * @author Pan
 * @reNameDb 修改db名字
 * @addData 增加表行
 * @deleteData 根据主键删除表
 * @deleteByWhere 根据条件删除表
 * @deleteTable 删除全表
 * @selectAll 查询全表
 * @selectByWhere 根据条件查询
 * @updateByWhere 修改表
 */
public class dbUtil {

    private static String DB_NAME = "wutnews_db";

    /**
     * 自定义 db名字 默认为 IDo_db
     *
     * @param dbName 自定义db的名字
     */
    public static void reNameDb(String dbName) {
        DB_NAME = dbName;
    }

    /**
     * 添加数据 db名字为IDo_db 没有表则自动新建 建表对象一定要有id属性名
     *
     * @param context
     * @param obj     表名为obj类名
     */
    public static void addData(Context context, Object obj) {
        FinalDb.create(context, DB_NAME).save(obj);
    }

    /**
     * 删除数据 db名字为IDo_db
     *
     * @param context
     * @param obj     表名为obj类名
     */
    public static void deleteData(Context context, Object obj) {
        FinalDb.create(context, DB_NAME).delete(obj);
    }

    /**
     * 删除数据 自定义条件 db名字为IDo_db
     *
     * @param context
     * @param clazz    表对象.class
     * @param strWhere 删除条件 栗子:"id=1" 表名为obj类名
     */
    public static void deleteByWhere(Context context, Class<?> clazz,
                                     String strWhere) {
        FinalDb.create(context, DB_NAME).deleteByWhere(clazz, strWhere);
    }

    /**
     * 删除表
     *
     * @param context
     * @param clazz   表对象.class
     */
    public static void deleteTable(Context context, Class<?> clazz) {
        FinalDb.create(context, DB_NAME).deleteAll(clazz);

    }

    /**
     * 查询表 返回表所有数据
     *
     * @param context
     * @param clazz   查询表对象.class
     */
    public static List<?> selectAll(Context context, Class<?> clazz) {
        return FinalDb.create(context, DB_NAME).findAll(clazz);
    }

    /**
     * 查询表 自定义条件 返回表所有数据
     *
     * @param context
     * @param clazz    查询表对象.class
     * @param strWhere 查询条件 栗子:"id=1" 查询表对象.class
     */
    public static List<?> selectByWhere(Context context, Class<?> clazz,
                                        String strWhere) {
        return FinalDb.create(context, DB_NAME).findAllByWhere(clazz, strWhere);

    }

    /**
     * 修改数据 自定义条件 返回表所有数据
     *
     * @param context
     * @param obj      表对象,set修改内容
     * @param strWhere 查询条件 栗子:"id=1"
     */
    public static void updateByWhere(Context context, Object obj,
                                     String strWhere) {
        FinalDb.create(context, DB_NAME).update(obj, strWhere);


    }


}
