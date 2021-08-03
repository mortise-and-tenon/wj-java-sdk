package fun.mortnon.wj.test.util.api;

import fun.mortnon.wj.model.Org;
import fun.mortnon.wj.model.OrgUser;
import fun.mortnon.wj.model.Group;
import fun.mortnon.wj.model.WjPage;
import fun.mortnon.wj.model.utils.JacksonUtil;
import fun.mortnon.wj.service.WjAddressListService;
import fun.mortnon.wj.service.WjService;
import fun.mortnon.wj.service.WjStorageConfig;
import fun.mortnon.wj.service.impl.WjDefaultStorageConfigImpl;
import fun.mortnon.wj.service.impl.WjServiceImpl;
import fun.mortnon.wj.vo.CreateGroupResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static fun.mortnon.wj.test.util.WjTestConst.APP_ID;
import static fun.mortnon.wj.test.util.WjTestConst.SECRET;

/**
 * 团队通讯录管理测试用例
 *
 * @author dongfangzan
 * @date 3.8.21 11:09 上午
 */
public class WjAddressListServiceTest {
    /** 问卷基础服务 */
    private WjService wjService;

    private WjAddressListService wjAddressListService;

    @Before
    public void init() {
        WjStorageConfig wjStorageConfig = new WjDefaultStorageConfigImpl();
        wjService = new WjServiceImpl(wjStorageConfig, APP_ID, SECRET);
        wjAddressListService = wjService.getWjAddressListService();
    }

    @Test
    public void testGetOrg() {
        Org org = wjAddressListService.getOrg(60005907214L);
        System.out.println(JacksonUtil.objectToJson(org));

        Assert.assertNotNull(org);
    }

    @Test
    public void testListOrgUsers() {
        List<Long> roles = new ArrayList<>();
        roles.add(0L);
        roles.add(2L);
        roles.add(3L);
        WjPage<OrgUser> orgUserWjPage = wjAddressListService.listOrgUsers(60005907214L, roles, 1, 20);
        System.out.println(JacksonUtil.objectToJson(orgUserWjPage));

        Assert.assertNotNull(orgUserWjPage);
    }

    @Test
    public void testListGroup() {
        WjPage<Group> teamGroupWjPage = wjAddressListService.listGroup(28042L, 1, 20, null, null);
        System.out.println(JacksonUtil.objectToJson(teamGroupWjPage));
    }

    @Test
    public void testCreateGroup() {
        CreateGroupResult testGroup = wjAddressListService.createGroup("测试分组", 0L, 1, "12345", 28042L);
        System.out.println(JacksonUtil.objectToJson(testGroup));
    }

    @Test
    public void testUpdateGroup() {
        wjAddressListService.updateGroup(28042L, 948153L, "测试分组2", 2, null, null);
        WjPage<Group> teamGroupWjPage = wjAddressListService.listGroup(28042L, 1, 20, null, null);
        System.out.println(JacksonUtil.objectToJson(teamGroupWjPage));
    }

    @Test
    public void testDeleteGroup() {
        List<Long> ids = new ArrayList<>();
        ids.add(948153L);
        ids.add(948216L);
        wjAddressListService.deleteGroup(28042L, ids);
        WjPage<Group> teamGroupWjPage = wjAddressListService.listGroup(28042L, 1, 20, null, null);
        System.out.println(JacksonUtil.objectToJson(teamGroupWjPage));
    }
}
