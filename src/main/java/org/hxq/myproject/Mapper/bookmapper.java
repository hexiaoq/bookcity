package org.hxq.myproject.Mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.hxq.myproject.pojo.BOOK;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface bookmapper {
    @Select("insert into t_book(name,price,author,sales,stock,img_path) values(#{name},#{price},#{author},#{sales},#{stock},#{img_path})")
    public void addbook(BOOK book);
    @Select("select id,name,price,author,sales,stock,img_path from t_book where id=#{id} ")
    public BOOK querybookbyid(Integer id);

    @Select("update t_book set name=#{name},price=#{price},author=#{author},sales=#{sales},stock=#{stock} where id=#{id}")
    public void updatebook(BOOK book) ;
    @Select("delete from t_book where id=#{id}")
    public void deletebookbyid(Integer id) ;

    @Select("select id,name,price,author,sales,stock,img_path from t_book")
    public List<BOOK> queryallbooks() ;

    //返回分页后的当前页面中的数据
    @Select("select id,name,price,author,sales,stock,img_path from t_book limit #{begin},#{pagesize}")
    public List<BOOK> querypageitems(int begin, Integer pagesize);


    //获得表格中的数据总数
    @Select("select count(*) from t_book ")
    public int querypagetotal() ;

    @Select("SELECT COUNT(*) FROM t_book WHERE price BETWEEN #{min} and #{max}" )
    public int querypagetotalbyprice(int min,int max);

@Select("select id,name,price,author,sales,stock,img_path from t_book WHERE price BETWEEN #{min} and #{max} limit #{begin},#{pagesize}")
    public List<BOOK> querypageitemsbyprice(int min,int max,int begin,int pagesize);
}
