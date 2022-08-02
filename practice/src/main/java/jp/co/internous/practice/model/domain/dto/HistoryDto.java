package jp.co.internous.practice.model.domain.dto;

import java.sql.Timestamp;

import jp.co.internous.practice.model.domain.Goods;
import jp.co.internous.practice.model.domain.Purchase;

public class HistoryDto {
	
	/** Purchase.id -Primary key */
	private long id;
	/** Purchase.user_id */
	private long userId;
	/** Purchase.goods_id */
	private long goodsId;
	/** Goods.goodsName */
	private String goodsName;
	/** Purchase.item_count */
	private long itemCount;
	/** Purchase.total */
	private long total;
	/** Purchase.created_at */
	private Timestamp createdAt;
	
	public HistoryDto() {}
	
	public HistoryDto(Purchase purchase, Goods goods) {
		this.setId(purchase.getId());
		this.setUserId(purchase.getUserId());
		this.setGoodsId(purchase.getGoodsId());
		this.setGoodsName(goods.getGoodsName());
		this.setItemCount(purchase.getItemCount());
		this.setTotal(purchase.getTotal());
		this.setCreatedAt(purchase.getCreatedAt());
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}
	
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	public long getItemCount() {
		return itemCount;
	}
	public void setItemCount(long itemCount) {
		this.itemCount = itemCount;
	}
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	

}
