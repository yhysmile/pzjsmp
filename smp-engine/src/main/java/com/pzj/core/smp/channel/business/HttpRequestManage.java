package com.pzj.core.smp.channel.business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.smp.channel.common.ChannelAccessConstant;
import com.pzj.core.smp.channel.model.HttpRequest;
import com.pzj.core.smp.channel.model.SendSmsChannelResp;
import com.pzj.core.smp.common.exception.SmpException;
import com.pzj.framework.converter.JSONConverter;

@Component("httpRequestManage")
public class HttpRequestManage {
	private static final Logger logger = LoggerFactory.getLogger(HttpRequestManage.class);

	/**
	 * http get请求
	 * @param requestModel
	 * @return SendSmsChannelResp
	 * @throws IOException 
	 */
	public SendSmsChannelResp doGet(HttpRequest requestModel) throws IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("http request doGet param:{}", JSONConverter.toJson(requestModel));
		}
		SendSmsChannelResp sendSmsChannelRespModel = new SendSmsChannelResp();
		URL remoteURL = null;
		try {
			remoteURL = new URL(requestModel.getUrl());
		} catch (MalformedURLException e) {
			logger.error("http request doGet send message fail! request : {}", JSONConverter.toJson(requestModel), e);
			throw new SmpException(e);
		}

		URLConnection connection = null;
		HttpURLConnection httpConnection = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			connection = remoteURL.openConnection();
			httpConnection = (HttpURLConnection) connection;
			httpConnection.setConnectTimeout(ChannelAccessConstant.CONNECTION_TIMEOUT);
			httpConnection.setReadTimeout(ChannelAccessConstant.READ_TIMEOUT);
			httpConnection.setRequestProperty("Accept-Charset", requestModel.getReqCharacter());
			httpConnection.setRequestProperty("Content-Type", ChannelAccessConstant.REQUEST_CONTENT_TYPE);

			sendSmsChannelRespModel.setCode(httpConnection.getResponseCode());
			//			if (sendSmsChannelRespModel.getCode() >= 300) {
			//				logger.error("http request return value error!!!,request param:{}", JSONConverter.toJson(requestModel));
			//				throw new SmpException(SmpExceptionCode.HTTP_RESP_ERR);
			//			}
			inputStream = httpConnection.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, requestModel.getRespCharacter());
			bufferedReader = new BufferedReader(inputStreamReader);
			StringBuffer respBuffer = new StringBuffer("");
			String tempLine = null;
			while ((tempLine = bufferedReader.readLine()) != null) {
				respBuffer.append(tempLine);
			}

			sendSmsChannelRespModel.setContent(respBuffer.toString());
		} finally {
			try {
				if (null != bufferedReader) {
					bufferedReader.close();
				}
				if (null != inputStreamReader) {
					inputStreamReader.close();
				}
				if (null != inputStream) {
					inputStream.close();
				}
				if (null != httpConnection) {
					httpConnection.disconnect();
				}
			} catch (IOException e) {
				logger.error("http request doGet send sms fail! request:{}", JSONConverter.toJson(requestModel), e);
				throw new SmpException(e);
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("http response doGet result:{}", JSONConverter.toJson(sendSmsChannelRespModel));
		}
		return sendSmsChannelRespModel;
	}

	/**
	 * http post请求
	 * @param requestModel
	 * @return SendSmsChannelResp
	 * @throws IOException 
	 */
	public SendSmsChannelResp doPost(HttpRequest requestModel) throws IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("http request doPost param:{}", JSONConverter.toJson(requestModel));
		}
		SendSmsChannelResp sendSmsChannelRespModel = new SendSmsChannelResp();
		URL url = null;
		try {
			url = new URL(requestModel.getUrl());
		} catch (MalformedURLException e) {
			logger.error("http request doPost send sms fail! request : {}", JSONConverter.toJson(requestModel));
			throw new SmpException(e);
		}

		URLConnection urlConn = null;
		HttpURLConnection httpConnection = null;
		OutputStream outputStream = null;
		OutputStreamWriter outputWriter = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			urlConn = url.openConnection();
			httpConnection = (HttpURLConnection) urlConn;
			httpConnection.setDoInput(Boolean.TRUE);
			httpConnection.setDoOutput(Boolean.TRUE);
			httpConnection.setConnectTimeout(ChannelAccessConstant.CONNECTION_TIMEOUT);
			httpConnection.setReadTimeout(ChannelAccessConstant.READ_TIMEOUT);
			httpConnection.setRequestMethod(ChannelAccessConstant.REQUEST_POST);
			httpConnection.setRequestProperty("Accept-Charset", requestModel.getReqCharacter());
			httpConnection.setRequestProperty("Content-Type", ChannelAccessConstant.REQUEST_CONTENT_TYPE);
			httpConnection.setRequestProperty("Content-Length", String.valueOf(requestModel.getParams().length()));

			outputStream = httpConnection.getOutputStream();
			outputWriter = new OutputStreamWriter(outputStream);
			outputWriter.write(requestModel.getParams());
			outputWriter.flush();

			sendSmsChannelRespModel.setCode(httpConnection.getResponseCode());
			//			if (sendSmsChannelRespModel.getCode() >= 300) {
			//				logger.error("http post request error!!!, request param:{}", JSONConverter.toJson(requestModel));
			//				throw new SmpException(SmpExceptionCode.HTTP_RESP_ERR);
			//			}

			inputStream = httpConnection.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, requestModel.getRespCharacter());
			bufferedReader = new BufferedReader(inputStreamReader);
			StringBuffer respBuffer = new StringBuffer("");
			String tempLine = null;
			while ((tempLine = bufferedReader.readLine()) != null) {
				respBuffer.append(tempLine);
			}

			sendSmsChannelRespModel.setContent(respBuffer.toString());
		} finally {
			try {
				if (null != bufferedReader) {
					bufferedReader.close();
				}
				if (null != inputStreamReader) {
					inputStreamReader.close();
				}
				if (null != inputStream) {
					inputStream.close();
				}
				if (null != outputWriter) {
					outputWriter.close();
				}
				if (null != outputStream) {
					outputStream.close();
				}
				if (null != httpConnection) {
					httpConnection.disconnect();
				}
			} catch (IOException e) {
				logger.error("http request doPost send sms fail! request:{}", JSONConverter.toJson(requestModel), e);
				throw new SmpException(e);
			}

		}
		if (logger.isDebugEnabled()) {
			logger.debug("http response doPost result:{}", JSONConverter.toJson(sendSmsChannelRespModel));
		}
		return sendSmsChannelRespModel;
	}
}
