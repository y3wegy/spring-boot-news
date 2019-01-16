package com.y3wegy.mapper.business;

import com.y3wegy.bean.business.FileDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author y3wegy
 */
@Mapper
public interface FileMapper {

    /**
     * -------------------------------------------------------------
     * @author     @date        @comment
     * Chen, Rui   1/14/2019     init version
     * -------------------------------------------------------------
     * @param fileDto
     */
    void save(FileDto fileDto);

    /**
     * -------------------------------------------------------------
     * @author     @date        @comment
     * Chen, Rui   1/14/2019     init version
     * -------------------------------------------------------------
     * @param fileId
     * @return
     */
    byte[] query(String fileId);

    /**
     * -------------------------------------------------------------
     * @author     @date        @comment
     * Chen, Rui   1/14/2019     init version
     * -------------------------------------------------------------
     * @param fileId
     */
    void delete(String fileId);
}
