package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bean.ProductBean;
import com.service.ProductService;

@Repository
public class ProductDao implements ProductService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public int addProduct(ProductBean productBean) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("insert into product(pname,pprice,pqty,pdescription,pstatus)values(?,?,?,?,?)",
				productBean.getpName(), productBean.getpPrice(), productBean.getpQty(), productBean.getpDesc(),
				productBean.ispStatus());
	}

	@Override
	public int deleteProduct(int pId) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("delete from product where pid = ?", pId);
	}

	@Override
	public int updateProduct(ProductBean productBean) {
		return jdbcTemplate.update("update product set pname =?,pprice=?,pqty=?,pdescription=?,pstatus=? where pid =?",
				productBean.getpName(), productBean.getpPrice(), productBean.getpQty(), productBean.getpDesc(),
				productBean.ispStatus(), productBean.getpId());
	}

	private final static class ProductMapper implements RowMapper<ProductBean> {

		@Override
		public ProductBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProductBean productBean = new ProductBean();
			productBean.setpId(rs.getInt("pid"));
			productBean.setpName(rs.getString("pname"));
			productBean.setpPrice(rs.getInt("pprice"));
			productBean.setpQty(rs.getInt("pqty"));
			productBean.setpStatus(rs.getBoolean("pstatus"));
			productBean.setpDesc(rs.getString("pdescription"));
			productBean.setCreated_at(rs.getTimestamp("created_at"));
			return productBean;
		}

	}

	@Override
	public List<ProductBean> getAllProducts() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select * from product", new ProductMapper());
	}

	@Override
	public ProductBean getProductById(int pId) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject("select * from product where pid = " + pId + "", new ProductMapper());
	}

}
