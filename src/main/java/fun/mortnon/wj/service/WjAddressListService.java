package fun.mortnon.wj.service;

import fun.mortnon.wj.model.*;
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

    /**
     * 修改分组
     * @link {https://wj.qq.com/docs/openapi/contact/group/update_group}
     *
     * @param teamId  企业ID
     * @param groupId 分组ID
     * @param name    分组名称
     * @param order   排序
     * @param fromId  原来所属的分组ID
     * @param toId    移动到新的分组下（移动分组时使用）
     */
    void updateGroup(Long teamId, Long groupId, String name, Integer order, Long fromId, Long toId);

    /**
     * 删除分组
     * @link {https://wj.qq.com/docs/openapi/contact/group/delete_group}
     *
     * @param teamId   企业ID
     * @param groupIds 分组id列表
     */
    void deleteGroup(Long teamId, List<Long> groupIds);

    // 成员

    /**
     * 按团队获取成员列表
     * @link {https://wj.qq.com/docs/openapi/contact/user/list_team_user}
     *
     * @param teamId  企业id
     * @param page    当前页码
     * @param perPage 每页显示条数
     * @param roles   角色
     * @return        团队成员
     */
    WjPage<TeamUser> listTeamUser(Long teamId, Integer page, Integer perPage, List<Long> roles);

    /**
     * 按分组获取成员列表
     * @link {https://wj.qq.com/docs/openapi/contact/user/list_group_user}
     *
     * @param teamId  企业id
     * @param groupId 分组id
     * @param page    当前页码
     * @param perPage 每页显示条数
     * @param roles   角色
     * @return        团队成员
     */
    WjPage<TeamUser> listGroupUser(Long teamId, Long groupId, Integer page, Integer perPage, List<Long> roles);

    /**
     * 添加分组成员
     * 企业使用该接口前，需要提前获得用户的 user_id，有两种方法:
     * 方法1： 从通讯录页面邀请成员加入企业后，通过获取成员列表 获得 user_id
     * 方法2： 使用注册用户创建一个第三方账号，获得 user_id
     * @link {https://wj.qq.com/docs/openapi/contact/user/create_user}
     *
     * @param teamId  企业ID
     * @param groupId 分组ID
     * @param userId  用户ID
     * @param openId  用户在第三方平台的唯一标识
     */
    void createUser(Long teamId, Long groupId, Long userId, String openId);

    /**
     * 修改成员
     * @link {https://wj.qq.com/docs/openapi/contact/user/update_user}
     *
     * @param teamId   团队ID
     * @param userId  用户ID
     * @param groupIds 分组ID
     * @param role     角色，受企业可用人数限制
     * @param openId   用户在第三方平台的唯一标识
     */
    void updateUser(Long teamId, Long userId, List<Long> groupIds, Long role, String openId);

    /**
     * 删除用户
     * @link {https://wj.qq.com/docs/openapi/contact/user/delete_user}
     *
     * @param teamId  企业ID
     * @param groupId 分组ID
     * @param userId  用户ID
     */
    void deleteUser(Long teamId, Long groupId, Long userId);
}
