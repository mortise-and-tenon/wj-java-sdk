package fun.mortnon.wj.service;

import fun.mortnon.wj.model.Org;
import fun.mortnon.wj.model.OrgUser;
import fun.mortnon.wj.model.TeamGroup;
import fun.mortnon.wj.model.WjPage;

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
     * @link {https://wj.qq.com/docs/openapi/org/get_org/}
     * 功能：按企业详细信息
     * 补充说明：每个企业绑定一个通讯录团队，在团队下可以创建分组、添加成员。
     * 在进行通讯录团队管理时，接口均基于团队ID(team_id)，仅当进行企业管理时，接口基于企业ID(id)。
     *
     * @param id 企业ID，登录 https://wj.qq.com 后右上角-头像-团队管理对应链接的org={}参数获得
     * @return   企业信息
     */
    Org getOrg(Long id);

    /**
     * @link {https://wj.qq.com/docs/openapi/org/get_org_users/}
     * 功能：按角色获取企业管理员
     *
     * @param orgId   企业ID
     * @param roles   获取角色
     * @param page    页码
     * @param perPage 每页条数
     * @return        管理员信息
     */
    WjPage<OrgUser> listOrgUsers(Long orgId, List<Long> roles, Integer page, Integer perPage);

    // 分组

    /**
     * @link {https://wj.qq.com/docs/openapi/contact/group/list_group/}
     *
     * @param teamId    企业id
     * @param page      当前页码
     * @param perPage   每页显示条数
     * @param remoteIds -------
     * @param parentId  上级ID
     * @return          分组列表
     */
    WjPage<TeamGroup> listTeamGroup(Long teamId, Integer page, Integer perPage, List<String> remoteIds, Long parentId);

    // 成员
}
