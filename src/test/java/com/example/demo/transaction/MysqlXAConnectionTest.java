package com.example.demo.transaction;

import com.mysql.jdbc.jdbc2.optional.MysqlXAConnection;
import com.mysql.jdbc.jdbc2.optional.MysqlXid;

import javax.sql.XAConnection;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created by liuyumeng on 2018/10/18.
 *
 * 通过JDBC操作MySql XA事务
 */
public class MysqlXAConnectionTest {
    public static void main(String[] args) {
        try {
            Connection conn1 =  DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
            XAConnection xaConn1 = new MysqlXAConnection((com.mysql.jdbc.Connection) conn1, true);

            //资源管理器操 1 操作接口
            XAResource rm1 = xaConn1.getXAResource();

            Connection conn2 =  DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
            XAConnection xaConn2 = new MysqlXAConnection((com.mysql.jdbc.Connection) conn2, true);

            //资源管理器 2 操作接口
            XAResource rm2 = xaConn2.getXAResource();

            //模拟请求TM，生成一个全局事务ID
            byte[] gtrid = "g12345".getBytes();
            int formatId = 1;
            // ==============分别执行RM1和RM2上的事务分支====================
            //TM生成rm1上的事务分支id
            byte[] bqual1 = "b00001".getBytes();
            Xid xid1 = new MysqlXid(gtrid,bqual1,formatId);

            // 执行rm1上的事务分支
            rm1.start(xid1, XAResource.TMNOFLAGS);//One of TMNOFLAGS, TMJOIN, or TMRESUME.
            PreparedStatement ps1 = conn1.prepareStatement("insert into test.uc(name,safeBrowsing) value('2',2);\n");
            ps1.execute();
            rm1.end(xid1, XAResource.TMSUCCESS);

            //TM生成rm2上的事务分支id
            byte[] bqual2 = "b00002".getBytes();
            Xid xid2 = new MysqlXid(gtrid,bqual2,formatId);

            // 执行rm2上的事务分支
            rm2.start(xid2, XAResource.TMNOFLAGS);//One of TMNOFLAGS, TMJOIN, or TMRESUME.
            PreparedStatement ps2 = conn2.prepareStatement("insert into test.uc(name,safeBrowsing) value('3',3);\n");
            ps2.execute();
            rm2.end(xid2, XAResource.TMSUCCESS);

            // ===================两阶段提交================================
            // phase1：询问所有的RM 准备提交事务分支
            int rm1_prepare = rm1.prepare(xid1);
            int rm2_prepare = rm2.prepare(xid2);

            // phase2：提交所有事务分支
            boolean onePhase = false; //TM判断有2个事务分支，所以不能优化为一阶段提交
            if (rm1_prepare == XAResource.XA_OK
                    && rm2_prepare == XAResource.XA_OK
                    ) {//所有事务分支都prepare成功，提交所有事务分支
                rm1.commit(xid1, onePhase);
                rm2.commit(xid2, onePhase);
            } else {//如果有事务分支没有成功，则回滚
                rm1.rollback(xid1);
                rm1.rollback(xid2);
            }

            } catch (Exception e) {
            //TODO 异常情况回滚
            e.printStackTrace();
        }
    }
}
