package generate;

import java.util.List;

import config.Constant.GenerateCodeType;
import vo.Table;

/**
 * 生成代码和创建文件接口
 * @className Generate
 * @author jwSun
 * @date 2018年4月18日 上午11:15:40
 * @version 1.0.0
 */
public interface Generate {

    public Generate init(GenerateCodeType generateCodeType);

    public void generate(Table table) throws Exception;

    public void generate(List<Table> tables) throws Exception;

}
