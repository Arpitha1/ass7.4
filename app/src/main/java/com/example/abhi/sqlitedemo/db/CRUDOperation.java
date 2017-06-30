package com.example.abhi.sqlitedemo.db;

import com.example.abhi.sqlitedemo.vo.NameVO;

import java.util.List;


public interface CRUDOperation {

    /**
     *  Adding new NameVO
     * @param name
     */
    public void addNameVO(NameVO name);

    /**
     * Getting single NameVO
     * @param id
     * @return
     */
    public NameVO getNameVO(int id);

    /**
     * Getting All NameVO
     * @return
     */
    public List<NameVO> getAllNameVO();

    /**
     * Getting NameVO Count
     * @return
     */
    public int getNameVOCount();

    /**
     * Updating single NameVO
     * @param name
     * @return
     */
    public int updateNameVO(NameVO name);

    /**
     * Deleting single NameVO
     * @param name
     */
    public void deleteNameVO(NameVO name);

    /**
     * Deleting all NameVOs
     */
    public void deleteAllNames();

}
