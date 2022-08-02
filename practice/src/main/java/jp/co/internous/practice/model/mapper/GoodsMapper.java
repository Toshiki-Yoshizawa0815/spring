package jp.co.internous.practice.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import jp.co.internous.practice.model.domain.Goods;

@Mapper
public interface GoodsMapper {
	
	@Select("SELECT * FROM goods")
	List<Goods> findAll();
	
	@Delete("DELETE FROM goods WHERE Id = #{id}")
	boolean deleteById(long id);

}
