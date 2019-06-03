package com.y3wegy.web.mapper.business;

import com.y3wegy.web.bean.business.FileDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author y3wegy
 */
@Mapper
public interface FileMapper {

    /**
     * -------------------------------------------------------------
     *
     * @param fileDto
     * @author @date        @comment
     * Chen, Rui   1/14/2019     init version
     * -------------------------------------------------------------
     */
    void save(FileDto fileDto);

    /**
     * -------------------------------------------------------------
     *
     * @param fileId
     * @return
     * @author @date        @comment
     * Chen, Rui   1/14/2019     init version
     * -------------------------------------------------------------
     */
    byte[] query(String fileId);

    /**
     * -------------------------------------------------------------
     *
     * @param fileId
     * @author @date        @comment
     * Chen, Rui   1/14/2019     init version
     * -------------------------------------------------------------
     */
    void delete(String fileId);
}
