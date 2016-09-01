package com.miteno.dao.impl;
import org.springframework.stereotype.Repository;

import com.miteno.base.impl.BaseDaoImpl;
import com.miteno.dao.LogDao;
import com.miteno.entity.Log;


@Repository("logDao")
public class LogDaoImpl extends BaseDaoImpl<Log> implements LogDao
{
}
