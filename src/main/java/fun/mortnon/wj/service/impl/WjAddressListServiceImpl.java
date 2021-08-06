package fun.mortnon.wj.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import fun.mortnon.wj.constants.WjApiConstants;
import fun.mortnon.wj.model.*;
import fun.mortnon.wj.model.utils.AssertUtils;
import fun.mortnon.wj.model.utils.JacksonUtil;
import fun.mortnon.wj.model.utils.StringUtils;
import fun.mortnon.wj.service.WjAddressListService;
import fun.mortnon.wj.service.WjService;
import fun.mortnon.wj.vo.CreateGroupResult;
import fun.mortnon.wj.vo.WjBaseResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author dongfangzan
 * @date 31.7.21 6:47 下午
 */
public class WjAddressListServiceImpl implements WjAddressListService {

    /**
     * 基础服务
     */
    private final WjService wjService;

    public WjAddressListServiceImpl(WjService wjService) {
        this.wjService = wjService;
    }

    @Override
    public Org getOrg(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }

        RequestContent requestContent = new RequestContent()
                .setUrl(String.format(WjApiConstants.GET_ORG, id.toString()));

        WjBaseResponse<Org> orgWjBaseResponse = wjService.doGetWithToken(requestContent, () ->
                JacksonUtil.jsonToObject(requestContent.getResult(), new TypeReference<WjBaseResponse<Org>>() {
                }));
        return orgWjBaseResponse.getData();
    }

    @Override
    public WjPage<OrgUser> listOrgUsers(Long orgId, List<Long> roles, Integer page, Integer perPage) {
        AssertUtils.nonNull(page, ErrorCode.InvalidArgument, "页码不能为空");
        AssertUtils.nonNull(perPage, ErrorCode.InvalidArgument, "每页数量不能为空");
        AssertUtils.nonNull(orgId, ErrorCode.InvalidArgument, "企业ID不能为空");

        Map<String, Object> params = new HashMap<>();

        if (Objects.nonNull(roles) && roles.size() > 0) {
            params.put("roles", roles);
        }
        params.put("page", page);
        params.put("per_page", perPage);

        RequestContent requestContent = new RequestContent()
                .setUrl(String.format(WjApiConstants.LIST_ORG_USERS, orgId.toString()))
                .setParam(params);
        WjBaseResponse<WjPage<OrgUser>> orgWjBaseResponse = wjService.doGetWithToken(requestContent, () ->
                JacksonUtil.jsonToObject(requestContent.getResult(), new TypeReference<WjBaseResponse<WjPage<OrgUser>>>() {
                }));
        return orgWjBaseResponse.getData();
    }

    @Override
    public WjPage<Group> listGroup(Long teamId, Integer page, Integer perPage, List<String> remoteIds, Long parentId) {
        AssertUtils.nonNull(teamId, ErrorCode.InvalidArgument, "企业ID不能为空");
        AssertUtils.nonNull(page, ErrorCode.InvalidArgument, "页码不能为空");
        AssertUtils.nonNull(perPage, ErrorCode.InvalidArgument, "每页数量不能为空");

        Map<String, Object> params = new HashMap<>();
        if (Objects.nonNull(remoteIds) && remoteIds.size() > 0) {
            params.put("remote_ids", remoteIds);
        }

        if (Objects.nonNull(parentId)) {
            params.put("parent_id", parentId);
        }
        params.put("page", page);
        params.put("per_page", perPage);

        RequestContent requestContent = new RequestContent().setUrl(String.format(WjApiConstants.LIST_GROUP, teamId.toString()))
                .setParam(params);
        WjBaseResponse<WjPage<Group>> response = wjService.doGetWithToken(requestContent, () ->
                JacksonUtil.jsonToObject(requestContent.getResult(), new TypeReference<WjBaseResponse<WjPage<Group>>>() {
                }));
        return response.getData();
    }

    @Override
    public CreateGroupResult createGroup(String name, Long parentId, Integer order, String remoteId, Long teamId) {
        AssertUtils.nonNull(teamId, ErrorCode.InvalidArgument, "企业ID不能为空");
        AssertUtils.notBlank(name, ErrorCode.InvalidArgument, "分组名称不能为空");

        Map<String, Object> formBody = new HashMap<>();
        formBody.put("name", name);

        if (Objects.nonNull(parentId)) {
            formBody.put("parent_id", parentId);
        }

        if (Objects.nonNull(order)) {
            formBody.put("order", order);
        }

        if (Objects.nonNull(order)) {
            formBody.put("remote_id", remoteId);
        }


        RequestContent requestContent = new RequestContent().setUrl(String.format(WjApiConstants.CREATE_GROUP, teamId))
                .setFormBody(formBody);
        WjBaseResponse<CreateGroupResult> createTeamGroupResultWjBaseResponse = wjService.doPostWithToken(requestContent, () ->
                JacksonUtil.jsonToObject(requestContent.getResult(), new TypeReference<WjBaseResponse<CreateGroupResult>>() {
                }));
        return createTeamGroupResultWjBaseResponse.getData();
    }

    @Override
    public void updateGroup(Long teamId, Long groupId, String name, Integer order, Long fromId, Long toId) {
        AssertUtils.nonNull(teamId, ErrorCode.InvalidArgument, "企业ID不能为空");
        AssertUtils.nonNull(groupId, ErrorCode.InvalidArgument, "分组ID不能为空");
        AssertUtils.notBlank(name, ErrorCode.InvalidArgument, "分组名称不能为空");
        Map<String, Object> formBody = new HashMap<>();
        formBody.put("name", name);

        if (Objects.nonNull(order)) {
            formBody.put("order", order);
        }

        if (Objects.nonNull(fromId)) {
            formBody.put("from_id", fromId);
        }

        if (Objects.nonNull(toId)) {
            formBody.put("to_id", toId);
        }

        RequestContent requestContent = new RequestContent().setUrl(String.format(WjApiConstants.UPDATE_GROUP, teamId, groupId))
                .setFormBody(formBody);

        wjService.doPostWithToken(requestContent, () -> null);
    }

    @Override
    public void deleteGroup(Long teamId, List<Long> groupIds) {
        AssertUtils.nonNull(teamId, ErrorCode.InvalidArgument, "企业ID不能为空");
        AssertUtils.notEmpty(groupIds, ErrorCode.InvalidArgument, "分组id列表不能为空");
        Map<String, Object> formBody = new HashMap<>();
        formBody.put("group_ids", groupIds);

        RequestContent requestContent = new RequestContent().setUrl(String.format(WjApiConstants.BATCH_DELETE_GROUP, teamId))
                .setJsonBody(JacksonUtil.objectToJson(formBody));

        wjService.doPostWithToken(requestContent, () -> null);
    }

    @Override
    public WjPage<TeamUser> listTeamUser(Long teamId, Integer page, Integer perPage, List<Long> roles) {
        AssertUtils.nonNull(page, ErrorCode.InvalidArgument, "页码不能为空");
        AssertUtils.nonNull(perPage, ErrorCode.InvalidArgument, "每页数量不能为空");
        AssertUtils.nonNull(teamId, ErrorCode.InvalidArgument, "企业ID不能为空");

        Map<String, Object> params = new HashMap<>(3);
        params.put("page", page);
        params.put("per_page", perPage);

        if (Objects.nonNull(roles) && roles.size() > 0) {
            params.put("roles", roles);
        }

        RequestContent requestContent = new RequestContent().setUrl(String.format(WjApiConstants.LIST_TEAM_USER, teamId))
                .setParam(params);
        WjBaseResponse<WjPage<TeamUser>> wjPageWjBaseResponse = wjService
                .doGetWithToken(requestContent, () -> JacksonUtil.jsonToObject(requestContent.getResult(),
                        new TypeReference<WjBaseResponse<WjPage<TeamUser>>>() {
                        }));

        return wjPageWjBaseResponse.getData();
    }

    @Override
    public WjPage<TeamUser> listGroupUser(Long teamId, Long groupId, Integer page, Integer perPage, List<Long> roles) {
        AssertUtils.nonNull(page, ErrorCode.InvalidArgument, "页码不能为空");
        AssertUtils.nonNull(perPage, ErrorCode.InvalidArgument, "每页数量不能为空");
        AssertUtils.nonNull(teamId, ErrorCode.InvalidArgument, "企业ID不能为空");
        AssertUtils.nonNull(groupId, ErrorCode.InvalidArgument, "分组ID不能为空");

        Map<String, Object> params = new HashMap<>(3);
        params.put("page", page);
        params.put("per_page", perPage);

        if (Objects.nonNull(roles) && roles.size() > 0) {
            params.put("roles", roles);
        }

        RequestContent requestContent = new RequestContent().setUrl(String.format(WjApiConstants.LIST_GROUP_USER, teamId, groupId))
                .setParam(params);
        WjBaseResponse<WjPage<TeamUser>> wjPageWjBaseResponse = wjService
                .doGetWithToken(requestContent, () -> JacksonUtil.jsonToObject(requestContent.getResult(),
                        new TypeReference<WjBaseResponse<WjPage<TeamUser>>>() {
                        }));

        return wjPageWjBaseResponse.getData();
    }

    @Override
    public void createUser(Long teamId, Long groupId, Long userId, String openId) {
        AssertUtils.nonNull(teamId, ErrorCode.InvalidArgument, "企业ID不能为空");
        AssertUtils.nonNull(groupId, ErrorCode.InvalidArgument, "分组ID不能为空");
        AssertUtils.nonNull(userId, ErrorCode.InvalidArgument, "用户ID不能为空");

        Map<String, Object> formBody = new HashMap<>(1);

        if (StringUtils.isNotBlank(openId)) {
            formBody.put("open_id", openId);
        }

        RequestContent requestContent = new RequestContent()
                .setUrl(String.format(WjApiConstants.CREATE_USER, teamId, groupId, userId))
                .setFormBody(formBody);

        wjService.doPostWithToken(requestContent, () -> null);
    }

    @Override
    public void updateUser(Long teamId, Long userId, List<Long> groupIds, Long role, String openId) {
        AssertUtils.nonNull(teamId, ErrorCode.InvalidArgument, "企业ID不能为空");
        AssertUtils.nonNull(userId, ErrorCode.InvalidArgument, "用户ID不能为空");

        Map<String, Object> formBody = new HashMap<>(1);

        if (Objects.nonNull(groupIds) && groupIds.size() > 0) {
            formBody.put("group_ids", JacksonUtil.objectToJson(groupIds));
        }

        if (Objects.nonNull(role)) {
            formBody.put("role", role);
        }

        if (StringUtils.isNotBlank(openId)) {
            formBody.put("open_id", openId);
        }

        RequestContent requestContent = new RequestContent()
                .setUrl(String.format(WjApiConstants.UPDATE_USER, teamId, userId))
                .setFormBody(formBody);

        wjService.doPostWithToken(requestContent, () -> null);
    }

    @Override
    public void deleteUser(Long teamId, Long groupId, Long userId) {
        AssertUtils.nonNull(teamId, ErrorCode.InvalidArgument, "企业ID不能为空");
        AssertUtils.nonNull(groupId, ErrorCode.InvalidArgument, "分组ID不能为空");
        AssertUtils.nonNull(userId, ErrorCode.InvalidArgument, "用户ID不能为空");

        RequestContent requestContent = new RequestContent()
                .setUrl(String.format(WjApiConstants.DELETE_USER, teamId, groupId, userId));
        wjService.doPostWithToken(requestContent, () -> null);
    }
}
