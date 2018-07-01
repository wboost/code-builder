package util;

import config.Constant.GenerateDbType;
import config.Constant.GenerateOrmType;
import lombok.Data;

@Data
public class UserConfig {

    String charset;
    Boolean enableBaseRestful;
    Boolean enableRestful;
    GenerateOrmType generateOrmType;
    GenerateDbType generateDbType;

    public UserConfig(String charset, boolean enableBaseRestful, boolean enableRestful, GenerateOrmType generateOrmType,
            GenerateDbType generateDbType) {
        super();
        this.enableBaseRestful = enableBaseRestful;
        this.generateOrmType = generateOrmType;
        this.generateDbType = generateDbType;
        this.enableRestful = enableRestful;
        this.charset = charset;
    }

}
