package com.sorm.utils;

import com.sorm.bean.ColumnInfo;
import com.sorm.bean.JavaFieldGetSet;
import com.sorm.bean.TableInfo;
import com.sorm.core.DBManager;
import com.sorm.core.MySqlTypeConvertor;
import com.sorm.core.TableContext;
import com.sorm.core.TypeConvertor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 类 名 称：JavaFileUtils
 * 类 描 述：封装了生成Java文件（源代码）常用操作
 * 创建时间：2019/4/29 10:38
 * 创建人：Mical
 */
public class JavaFileUtils {

    /**
     * 根据字段信息生成 java 属性信息。如：varchar username --> private String username；以及相应的set和get方法源码
     * @param column    字段信息
     * @param convertor 类型转化器
     * @return java属性和set/get方法源码
     */
    public static JavaFieldGetSet createFieldGetSetSRC(ColumnInfo column, TypeConvertor convertor) {

        JavaFieldGetSet jfgs = new JavaFieldGetSet();

        String javaFieldType = convertor.databaseType2JavaType(column.getDataType());

        //生成属性的源码
        jfgs.setFieldInfo("\tprivate " + javaFieldType + " " + column.getName() + ";\n");

        //生成get方法的源码：public String getUsername(){return username;}
        StringBuilder getSrc = new StringBuilder();
        getSrc.append("\tpublic " + javaFieldType + " get" + StringUtils.firstChar2UpperCase(column.getName()) + "(){\n");
        getSrc.append("\t\treturn " + column.getName() + ";\n");
        getSrc.append("\t}\n");
        jfgs.setGetInfo(getSrc.toString());

        //生成set方法的源码：public void setUsername(String username){this.username=username;}
        StringBuilder setSrc = new StringBuilder();
        setSrc.append("\tpublic void set" + StringUtils.firstChar2UpperCase(column.getName()) + "(");
        setSrc.append(javaFieldType + " " + column.getName() + "){\n");
        setSrc.append("\t\tthis." + column.getName() + "=" + column.getName() + ";\n");
        setSrc.append("\t}\n");
        jfgs.setSetInfo(setSrc.toString());

        return jfgs;
    }

    /**
     * 根据表信息生成Java类的源代码
     *
     * @param tableInfo 表信息
     * @param convertor 数据类型转换器
     * @return 返回Java源码字符串
     */
    public static String createJavaStr(TableInfo tableInfo, TypeConvertor convertor) {

        Map<String, ColumnInfo> columns = tableInfo.getColumns();
        List<JavaFieldGetSet> javaFields = new ArrayList<>();

        for (ColumnInfo columnInfo : columns.values()) {
            if (columnInfo != null) {
                javaFields.add(createFieldGetSetSRC(columnInfo, convertor));
            }
        }

        StringBuilder src = new StringBuilder();

        //生成package语句
        src.append("package " + DBManager.getConf().getPoPackage() + ";\n\n");

        //生成import语句
        src.append("import java.sql.*;\n");
        src.append("import java.util.*;\n\n");

        //生成类声明语句
        src.append("public class " + StringUtils.firstChar2UpperCase(tableInfo.getTname()) + " {\n\n");

        //生成属性列表
        for (JavaFieldGetSet javaField : javaFields) {
            src.append(javaField.getFieldInfo());
        }
        src.append("\n\n");

        //生成get方法列表
        for (JavaFieldGetSet javaField : javaFields) {
            src.append(javaField.getGetInfo());
        }

        //生成set方法列表
        for (JavaFieldGetSet javaField : javaFields) {
            src.append(javaField.getSetInfo());
        }

        //生成类结束
        src.append("}\n");
        return src.toString();
    }

    /**
     * 将java源代码生成到文件中
     *
     * @param tableInfo 表信息
     * @param convertor 数据类型转化器
     */
    public static void createJavaPOFile(TableInfo tableInfo, TypeConvertor convertor) {
        String src = createJavaStr(tableInfo, convertor);

        String srcPath = DBManager.getConf().getSrcPath() + "\\";
        String pacakgePath = DBManager.getConf().getPoPackage().replaceAll("\\.", "/");

        File f = new File(srcPath + pacakgePath);
        System.out.println(f.getAbsolutePath());
        if (!f.exists()) { //如果指定目录不存在就创建
            f.mkdirs();
        }
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(f.getAbsolutePath() + "\\" + StringUtils.firstChar2UpperCase(tableInfo.getTname()) + ".java"));
            bw.write(src);
            System.out.println("建立表" + tableInfo.getTname() + "对应的java类：" + StringUtils.firstChar2UpperCase(tableInfo.getTname()) + ".java");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
