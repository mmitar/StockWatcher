package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * IEX Stock Model to consume JSON from API. 11 properties.
 * @author Matthew & Joey
 *
 */
@ManagedBean
@ViewScoped
public class Stock {
	
	private String symbol;
	private String date;
	private float open;
	private float high;
	private float low;
	private float close;
	private float volume;
	private float unadjustedVolume;
	private float change;
	private float changePercent;
	private float vwap;
	
	public Stock(String symbol, String date, float open, float high, float low, float close, float volume, float unadjustedVolume,
			float change, float changePercent, float vwap) {
		super();
		this.symbol = symbol;
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
		this.unadjustedVolume = unadjustedVolume;
		this.change = change;
		this.changePercent = changePercent;
		this.vwap = vwap;
	}
	
	public Stock() {
		symbol = "";
		date = "";
		open = 0;
		high = 0;
		low = 0;
		close = 0;
		volume = 0;
		unadjustedVolume = 0;
		change = 0;
		changePercent = 0;
		vwap = 0;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public float getOpen() {
		return open;
	}
	public void setOpen(float open) {
		this.open = open;
	}
	public float getHigh() {
		return high;
	}
	public void setHigh(float high) {
		this.high = high;
	}
	public float getLow() {
		return low;
	}
	public void setLow(float low) {
		this.low = low;
	}
	public float getClose() {
		return close;
	}
	public void setClose(float close) {
		this.close = close;
	}
	public float getVolume() {
		return volume;
	}
	public void setVolume(float volume) {
		this.volume = volume;
	}
	public float getUnadjustedVolume() {
		return unadjustedVolume;
	}
	public void setUnadjustedVolume(float unadjustedVolume) {
		this.unadjustedVolume = unadjustedVolume;
	}
	public float getChange() {
		return change;
	}
	public void setChange(float change) {
		this.change = change;
	}
	public float getChangePercent() {
		return changePercent;
	}
	public void setChangePercent(float changePercent) {
		this.changePercent = changePercent;
	}
	public float getVwap() {
		return vwap;
	}
	public void setVwap(float vwap) {
		this.vwap = vwap;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "Stock [symbol=" + symbol + ", date=" + date + ", open=" + open + ", high=" + high + ", low=" + low
				+ ", close=" + close + ", volume=" + volume + ", unadjustedVolume=" + unadjustedVolume + ", change="
				+ change + ", changePercent=" + changePercent + ", vwap=" + vwap + "]";
	}
	
	public static Stock getOneResultSet(ResultSet rs) throws SQLException
	{
		Stock stock = new Stock(
				rs.getString("SYMBOL"),
				rs.getString("DATE"),
				rs.getFloat("OPEN"),
				rs.getFloat("HIGH"),
				rs.getFloat("LOW"),
				rs.getFloat("CLOSE"),
				rs.getFloat("VOLUME"),
				rs.getFloat("UNADJUSTEDVOLUME"),
				rs.getFloat("CHANGE"),
				rs.getFloat("CHANGEPERCENT"),
				rs.getFloat("VWAP")
				);
				
		return stock;
	}
	
	public static List<Stock> getAllResultSet(ResultSet rs) throws SQLException
	{
		
		return null;
	}
	
	public static String getParamSet()
	{		
		return     "`SYMBOL`,"
				+ " `DATE`,"
				+ " `OPEN`,"
				+ " `HIGH`,"
				+ " `LOW`,"
				+ " `CLOSE`,"
				+ " `VOLUME`,"
				+ " `UNADJUSTEDVOLUME`,"
				+ " `CHANGE`,"
				+ " `CHANGEPERCENT`,"
				+ " `VWAP`";
	}
	
	public static String getValueSet(Stock stock)
	{
		String valueSet = 
			"'" + stock.getSymbol() + "', '"
				+ stock.getDate() + "', "
				+ stock.getOpen() + ", "
				+ stock.getHigh() + ", "
				+ stock.getLow() + ", "
				+ stock.getClose() + ", "
				+ stock.getVolume() + ", "
				+ stock.getUnadjustedVolume() + ", "
				+ stock.getChange() + ", "
				+ stock.getChangePercent() + ", "
				+ stock.getVwap();
		return valueSet;
	}
	
	public static String getFormatSet()
	{
		return "'%s', '%s', %f, %f, %f, %f, %f, %f, %f, %f, %f";
	}
	
	public static String getUpdateSetBySymbol(Stock stock) 
	{
		return  	"`DATE` = " + stock.getDate()
				+ ", `OPEN` = " + stock.getOpen()
				+ ", `HIGH` = " + stock.getHigh()
				+ ", `LOW` = " + stock.getLow()
				+ ", `CLOSE` = " + stock.getClose()
				+ ", `VOLUME` = " + stock.getVolume()
				+ ", `UNADJUSTEDVOLUME` = " + stock.getUnadjustedVolume()
				+ ", `CHANGE` = " + stock.getChange()
				+ ", `CHANGEPERCENT` = " + stock.getChangePercent()
				+ ", `VWAP` = " + stock.getVwap()
				+ " WHERE `SYMBOL` = " + stock.getSymbol();
	}
}
