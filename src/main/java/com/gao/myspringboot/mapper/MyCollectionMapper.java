package com.gao.myspringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gao.myspringboot.pojo.MyCollection;
import com.gao.myspringboot.pojo.Word;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MyCollectionMapper extends BaseMapper<MyCollection> {
    @Select("select word.* from studywords.word , studywords.mycollection WHERE word.wordId = mycollection.wordId and mycollection.userId = #{userId} ")
    Page<Word> getCollectionWordsByPage(IPage<Word> page, int userId);
}
