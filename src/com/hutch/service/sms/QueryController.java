package com.hutch.service.sms;

import java.util.List;

import com.hutch.service.sms.model.ResponseMessage;
import com.hutch.service.sms.model.USIMBase;
import com.hutch.service.sms.util.CommonUtill;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class QueryController 
{	
			
	public static ResponseMessage getResponseMsg(String query) throws SQLException 
	{
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement statement = null;
		ResponseMessage responseMessage = null;
		
		System.out.println("Query getStringData " + query);		

		try 
		{
			conn = DatabaseConnection.getInstance().getConnection();
			
			statement = conn.prepareStatement(query);
			rs = statement.executeQuery();

			while (rs.next()) 
			{
				responseMessage = new ResponseMessage();
				responseMessage.setMessageId(rs.getInt("id"));
				responseMessage.setMessageContent(rs.getString("msg_content"));
			}
		} 
		catch (SQLException e) 
		{
			throw new SQLException();
		} 
		finally 
		{
			if (conn != null) 
			{
				try
				{
					conn.close();
				} 
				catch (SQLException e) 
				{
					throw new SQLException();
				}
			}
			
			try 
			{
				if(rs != null)
					rs.close();
			} 
			catch (Exception e) 
			{
				
			}
			
			try 
			{
				if(statement != null)
					statement.close();
			} 
			catch (Exception e) 
			{
				
			}
		}

		return responseMessage;
	}
	
	public static String getStringData(String query, String colomnName) throws SQLException 
	{
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement statement = null;
		String msisdn = CommonUtill.EMPTY_STRING;
		
		System.out.println("Query getStringData " + query);		

		try 
		{
			conn = DatabaseConnection.getInstance().getConnection();
			
			statement = conn.prepareStatement(query);
			rs = statement.executeQuery();

			while (rs.next()) 
			{
				msisdn = rs.getString(colomnName);
			}
		} 
		catch (SQLException e) 
		{
			throw new SQLException();
		} 
		finally 
		{
			if (conn != null) 
			{
				try
				{
					conn.close();
				} 
				catch (SQLException e) 
				{
					throw new SQLException();
				}
			}
			
			try 
			{
				if(rs != null)
					rs.close();
			} 
			catch (Exception e) 
			{
				
			}
			
			try 
			{
				if(statement != null)
					statement.close();
			} 
			catch (Exception e) 
			{
				
			}
		}

		return msisdn;
	}

	public static boolean InsertData(String sql) throws SQLException 
	{

		System.out.println("Query InsertData : " + sql);

		boolean b = false;
		Connection con = null;
		Statement statement = null;
		
		try 
		{
		
			con = DatabaseConnection.getInstance().getConnection();
			statement = con.createStatement();
			statement.execute(sql);
			b = true;

		} 
		catch (Exception e) 
		{
			throw new SQLException();
		} 
		finally
		{
			if (con != null) 
			{
				try 
				{
					con.close();
				} catch (SQLException localSQLException1) 
				{
					throw new SQLException();
				}
				con = null;
			}
			
			try 
			{
				if(statement != null)
					statement.close();
			} 
			catch (Exception e) 
			{
				
			}
		}

		return b;
	}
	public static USIMBase getMobileFromUsimBase(String query) throws SQLException 
	{
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement statement = null;
		USIMBase usimBase = null;
		
		System.out.println("Query getMobileFromUsimBase " + query);	

		try 
		{
			conn = DatabaseConnection.getInstance().getConnection();
			
			statement = conn.prepareStatement(query);
			rs = statement.executeQuery();

			while (rs.next()) 
			{
				usimBase = new USIMBase();
				usimBase.setMsisdn(rs.getString("msisdn"));
				usimBase.setCalledStatus(rs.getInt("called_status"));
				usimBase.setReloadStatus(rs.getInt("reload_status"));
				usimBase.setUnicodeStatus(rs.getString("unicode_status"));
				usimBase.setPostPaidStatus(rs.getString("post_paid_status"));
				usimBase.setDateTime(rs.getString("updated_date_time"));
			}
		} 
		catch (SQLException e) 
		{
			throw new SQLException();
		} 
		finally 
		{
			if (conn != null) 
			{
				try
				{
					conn.close();
				} 
				catch (SQLException e) 
				{
					throw new SQLException();
				}
			}
			
			try 
			{
				if(rs != null)
					rs.close();
			} 
			catch (Exception e) 
			{
				
			}
			
			try 
			{
				if(statement != null)
					statement.close();
			} 
			catch (Exception e) 
			{
				
			}
		}

		return usimBase;
	}
	
}
