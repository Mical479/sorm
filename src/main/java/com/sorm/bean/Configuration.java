package com.sorm.bean;

/**
 * 类 名 称：Configuration
 * 类 描 述：管理配置信息
 * 创建时间：2019/4/29 10:42
 * 创建人：Mical
 */
public class Configuration {

    private String driver; //驱动类
    private String url; //jdbc的url
    private String user; //用户名
    private String pwd; //数据库密码
    private String usingDB; //正杂使用哪个数据库
    private String srcPath; //项目的源码路径
    private String poPackage; //扫描生成java类的包（po的意思是：Persistence object持久化对象）
    private String queryClass; //项目使用的查询类
    private int poolMinSize; //连接池中最小的连接数
    private int poolMaxSize; //连接池中最大的连接数

    public String getQueryClass() {
        return queryClass;
    }

    public void setQueryClass(String queryClass) {
        this.queryClass = queryClass;
    }

    public Configuration() {
    }

    public Configuration(String driver, String url, String user, String pwd, String usingDB, String srcPath, String poPackage) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.pwd = pwd;
        this.usingDB = usingDB;
        this.srcPath = srcPath;
        this.poPackage = poPackage;
    }

    public int getPoolMinSize() {
        return poolMinSize;
    }

    public void setPoolMinSize(int poolMinSize) {
        this.poolMinSize = poolMinSize;
    }

    public int getPoolMaxSize() {
        return poolMaxSize;
    }

    public void setPoolMaxSize(int poolMaxSize) {
        this.poolMaxSize = poolMaxSize;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUsingDB() {
        return usingDB;
    }

    public void setUsingDB(String usingDB) {
        this.usingDB = usingDB;
    }

    public String getSrcPath() {
        return srcPath;
    }

    public void setSrcPath(String srcPath) {
        this.srcPath = srcPath;
    }

    public String getPoPackage() {
        return poPackage;
    }

    public void setPoPackage(String poPackage) {
        this.poPackage = poPackage;
    }
}
