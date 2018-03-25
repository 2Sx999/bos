package cn.porkchop.bos.service.impl;

import cn.porkchop.bos.dao.DecidedZoneDao;
import cn.porkchop.bos.dao.NoticeBillDao;
import cn.porkchop.bos.dao.WorkbillDao;
import cn.porkchop.bos.domain.*;
import cn.porkchop.bos.service.NoticeBillSerivce;
import cn.porkchop.bos.utils.BOSUtils;
import cn.porkchop.crm.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

@Service
@Transactional
public class NoticeBillSerivceImpl implements NoticeBillSerivce {
    @Autowired
    private NoticeBillDao noticeBillDao;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DecidedZoneDao decidedZoneDao;
    @Autowired
    private WorkbillDao workbillDao;

    @Override
    public void add(Noticebill model) {
        User loginUser = BOSUtils.getLoginUser();
        model.setUser(loginUser);
        noticeBillDao.save(model);
        //获取收货地址
        String pickaddress = model.getPickaddress();
        //根据客户的地址获取定区id
        String decidedZoneId = customerService.findDecidedZoneIdByAddress(pickaddress);
        if (decidedZoneId != null) {
            //客户信息中有这个地址,可以完成自动分单
            Decidedzone decidedzone = decidedZoneDao.findById(decidedZoneId);
            //获得负责这个定区的取派员
            Staff staff = decidedzone.getStaff();
            //设置分单类型为自动分单
            model.setOrdertype(Noticebill.ORDERTYPE_AUTO);
            //生成一个工单
            Workbill workbill = new Workbill();
            //追单次数
            workbill.setAttachbilltimes(0);
            workbill.setNoticebill(model);
            workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
            //设置取件状态为未取件
            workbill.setPickstate(Workbill.PICKSTATE_NO);
            workbill.setRemark(model.getRemark());
            workbill.setStaff(staff);
            workbill.setType(Workbill.TYPE_1);
            workbillDao.save(workbill);
            //然后发送短信给取派员


        } else {
            //未查询到属于的定区,设置为人工分单
            model.setOrdertype(Noticebill.ORDERTYPE_MAN);
        }
    }
}
