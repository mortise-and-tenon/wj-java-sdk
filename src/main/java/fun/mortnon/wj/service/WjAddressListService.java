package fun.mortnon.wj.service;

import fun.mortnon.wj.model.Org;
import fun.mortnon.wj.model.OrgUser;
import fun.mortnon.wj.model.Group;
import fun.mortnon.wj.model.WjPage;
import fun.mortnon.wj.vo.CreateGroupResult;

import java.util.List;

/**
 * 团队通讯录管理
 *
 * @author dongfangzan
 * @date 27.7.21 10:59 上午
 */
public interface WjAddressListService {

    // 企业
    /**
     * 功能：按企业详细信息
     * 补充说明：每个企业绑定一个通讯录团队，在团队下可以创建分组、添加成员。
     * 在进行通讯录团队管理时，接口均基于团队ID(team_id)，仅当进行企业管理时，接口基于企业ID(id)。
     * @link {https://wj.qq.com/docs/openapi/org/get_org/}
     *
     * @param id 企业ID，登录 https://wj.qq.com 后右上角-头像-团队管理对应链接的org={}参数获得
     * @return   企业信息
     */
    Org getOrg(Long id);

    /**
     * 功能：按角色获取企业管理员
     * @link {https://wj.qq.com/docs/openapi/org/get_org_users/}
     *
     * @param orgId   企业ID
     * @param roles   获取角色   0:企业创建者;2:企业管理员;3:问卷管理员，不填则无数据
     * @param page    页码
     * @param perPage 每页条数
     * @return        管理员信息
     */
    WjPage<OrgUser> listOrgUsers(Long orgId, List<Long> roles, Integer page, Integer perPage);

    // 分组

    /**
     * 获取分组列表
     * @link {https://wj.qq.com/docs/openapi/contact/group/list_group/}
     *
     * @param teamId    企业id
     * @param page      当前页码
     * @param perPage   每页显示条数
     * @param remoteIds -------
     * @param parentId  上级ID
     * @return          分组列表
     */
    WjPage<Group> listGroup(Long teamId, Integer page, Integer perPage, List<String> remoteIds, Long parentId);


    /**
     * 新建分组
     * @link {https://wj.qq.com/docs/openapi/contact/group/create_group}
     *
     * @param name     分组名称
     * @param parentId 上级分组ID
     * @param order    排序
     * @param remoteId 第三方系统的分组ID，用于关联
     * @param teamId   企业ID
     * @return         创建分组结果
     */
    CreateGroupResult createGroup(String name, Long parentId, Integer order, String remoteId, Long teamId);

    // 成员

    /**
     * 修改分组
     * @link {https://wj.qq.com/docs/openapi/contact/group/update_group}
     *
     * @param teamId
     * @param groupId
     * @param name
     * @param order
     * @param fromId
     * @param toId
     */
    void updateGroup(Long teamId, Long groupId, String name, Integer order, Long fromId, Long toId);
}
