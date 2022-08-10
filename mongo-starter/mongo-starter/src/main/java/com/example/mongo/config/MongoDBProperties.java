package com.example.mongo.config;

import com.mongodb.ConnectionString;
import org.bson.UuidRepresentation;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-08-04 16:18
 * @Version V1.0
 */
@ConfigurationProperties(prefix = "demo.mongodb")
public class MongoDBProperties {

    /**
     * 配置的端口为空时使用的默认端口
     */
    public static final int DEFAULT_PORT = 27017;

    /**
     * 配置的URI为空时使用的默认URI
     */
    public static final String DEFAULT_URI = "mongodb://localhost/test";

    /**
     * Mongo服务器主机。无法使用URI设置
     */
    private String host;

    /**
     * Mongo服务器端口。无法使用URI设置
     */
    private Integer port = null;

    /**
     * Mongo数据库URI。覆盖主机、端口、用户名、密码和数据库。
     */
    private String uri;

    /**
     * 数据库名称
     */
    private String database;

    /**
     * 身份验证数据库名称
     */
    private String authenticationDatabase;

    private final Gridfs gridfs = new Gridfs();

    /**
     * username
     */
    private String username;

    /**
     * password
     */
    private char[] password;

    /**
     * Required replica set name for the cluster. Cannot be set with URI.
     */
    private String replicaSetName;

    /**
     * Fully qualified name of the FieldNamingStrategy to use.
     */
    private Class<?> fieldNamingStrategy;

    /**
     * Representation to use when converting a UUID to a BSON binary value.
     */
    private UuidRepresentation uuidRepresentation = UuidRepresentation.JAVA_LEGACY;

    /**
     * 是否启用自动索引创建
     */
    private Boolean autoIndexCreation;

    /**
     * 事务开关
     */
    private Boolean transactionEnabled;

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDatabase() {
        return this.database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getAuthenticationDatabase() {
        return this.authenticationDatabase;
    }

    public void setAuthenticationDatabase(String authenticationDatabase) {
        this.authenticationDatabase = authenticationDatabase;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return this.password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getReplicaSetName() {
        return this.replicaSetName;
    }

    public void setReplicaSetName(String replicaSetName) {
        this.replicaSetName = replicaSetName;
    }

    public Class<?> getFieldNamingStrategy() {
        return this.fieldNamingStrategy;
    }

    public void setFieldNamingStrategy(Class<?> fieldNamingStrategy) {
        this.fieldNamingStrategy = fieldNamingStrategy;
    }

    public UuidRepresentation getUuidRepresentation() {
        return this.uuidRepresentation;
    }

    public void setUuidRepresentation(UuidRepresentation uuidRepresentation) {
        this.uuidRepresentation = uuidRepresentation;
    }

    public String getUri() {
        return this.uri;
    }

    public String determineUri() {
        return (this.uri != null) ? this.uri : DEFAULT_URI;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getPort() {
        return this.port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Gridfs getGridfs() {
        return this.gridfs;
    }

    public String getMongoClientDatabase() {
        if (this.database != null) {
            return this.database;
        }
        return new ConnectionString(determineUri()).getDatabase();
    }

    public Boolean isAutoIndexCreation() {
        return this.autoIndexCreation;
    }

    public void setAutoIndexCreation(Boolean autoIndexCreation) {
        this.autoIndexCreation = autoIndexCreation;
    }

    public Boolean getAutoIndexCreation() {
        return autoIndexCreation;
    }

    public Boolean getTransactionEnabled() {
        return transactionEnabled;
    }

    public void setTransactionEnabled(Boolean transactionEnabled) {
        this.transactionEnabled = transactionEnabled;
    }

    public static class Gridfs {

        /**
         * GridFS database name.
         */
        private String database;

        /**
         * GridFS bucket name.
         */
        private String bucket;

        public String getDatabase() {
            return this.database;
        }

        public void setDatabase(String database) {
            this.database = database;
        }

        public String getBucket() {
            return this.bucket;
        }

        public void setBucket(String bucket) {
            this.bucket = bucket;
        }

    }

}
