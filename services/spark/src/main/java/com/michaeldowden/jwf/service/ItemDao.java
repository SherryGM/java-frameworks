package com.michaeldowden.jwf.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.michaeldowden.jwf.model.Bourbon;

public class ItemDao {
	private static final String CONFIG = "mybatis-config.xml";
	private static final String SCRIPT = "bourbon.sql";

	private SqlSessionFactory sqlSessionFactory;

	public ItemDao() {
		try {
			InputStream inputStream = Resources.getResourceAsStream(CONFIG);
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			sqlSessionFactory = builder.build(inputStream);

			// Run Setup Script
			SqlSession session = sqlSessionFactory.openSession();
			try {
				ScriptRunner runner = new ScriptRunner(session.getConnection());
				runner.runScript(Resources.getResourceAsReader(SCRIPT));
			} finally {
				session.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Bourbon> listItems() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ItemMapper mapper = session.getMapper(ItemMapper.class);
			return mapper.listItems();
		} finally {
			session.close();
		}
	}

	public Bourbon findBourbon(final Integer itemId) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ItemMapper mapper = session.getMapper(ItemMapper.class);
			return mapper.findBourbon(itemId);
		} finally {
			session.close();
		}
	}

	public Bourbon findBourbonByShortname(final String shortname) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ItemMapper mapper = session.getMapper(ItemMapper.class);
			return mapper.findBourbonByShortname(shortname);
		} finally {
			session.close();
		}
	}

}
