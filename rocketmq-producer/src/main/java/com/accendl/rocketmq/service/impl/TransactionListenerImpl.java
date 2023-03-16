/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.accendl.rocketmq.service.impl;

import com.accendl.account.dto.UserDTO;
import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * @author <a href="mailto:fangjian0423@gmail.com">Jim</a>
 */
@Component("myTransactionListener")
public class TransactionListenerImpl implements TransactionListener {

	private static final Logger logger = LoggerFactory.getLogger(TransactionListenerImpl.class);

	/**
	 * Execute local transaction.
	 * @param msg messages
	 * @param arg message args
	 * @return Transaction state
	 */
	@Override
	public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
		try {
			String username = msg.getProperty("username");
			if (StringUtils.hasText(username)){
				logger.info("executer: username="+username +" commit");
				return LocalTransactionState.COMMIT_MESSAGE;
			}else {
				logger.error("executer: " + new String(msg.getBody()) + " unknow");
				return LocalTransactionState.UNKNOW;
			}
		} catch (Exception e){
			logger.error("executer: " + new String(msg.getBody()) + " rollback");
			logger.error("Exception="+e.getMessage());
			return LocalTransactionState.ROLLBACK_MESSAGE;
		}
	}

	/**
	 * MQ check back local transaction states.
	 * @param msg messages
	 * @return Transaction state
	 */
	@Override
	public LocalTransactionState checkLocalTransaction(MessageExt msg) {
		int crc = msg.getBodyCRC();
		logger.info("check: " + crc);
		return LocalTransactionState.COMMIT_MESSAGE;
	}

}
