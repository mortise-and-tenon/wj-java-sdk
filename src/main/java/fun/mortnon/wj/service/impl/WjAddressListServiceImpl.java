package fun.mortnon.wj.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import fun.mortnon.wj.constants.WjApiConstants;
import fun.mortnon.wj.model.*;
import fun.mortnon.wj.model.utils.AssertUtils;
import fun.mortnon.wj.model.utils.JacksonUtil;
import fun.mortnon.wj.service.WjAddressListService;
import fun.mortnon.wj.service.WjService;
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

    /** 基础服务*/
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

        if (Objects.nonNull(roles) || roles.size() > 0) {
            params.put("roles", roles);
        }
        params.put("page", page);
        params.put("per_page", perPage);

        RequestContent requestContent = new RequestContent()
                .setUrl(String.format(WjApiConstants.LIST_ORG_USERS, orgId.toString()))
                .setParam(params);
        WjBaseResponse<WjPage<OrgUser>> orgWjBaseResponse = wjService.doPostWithToken(requestContent, () ->
                JacksonUtil.jsonToObject(requestContent.getResult(), new TypeReference<WjBaseResponse<WjPage<OrgUser>>>() {
                }));
        return orgWjBaseResponse.getData();
    }

    @Override
    public WjPage<TeamGroup> listTeamGroup(Long teamId, Integer page, Integer perPage, List<String> remoteIds, Long parentId) {
        AssertUtils.nonNull(teamId, ErrorCode.InvalidArgument, "企业ID不能为空");
        AssertUtils.nonNull(page, ErrorCode.InvalidArgument, "页码不能为空");
        AssertUtils.nonNull(perPage, ErrorCode.InvalidArgument, "每页数量不能为空");

        Map<String, Object> params = new HashMap<>();
        if (Objects.nonNull(remoteIds) || remoteIds.size() > 0) {
            params.put("remote_ids", remoteIds);
        }

        if (Objects.nonNull(parentId)) {
            params.put("parent_id", parentId);
        }
        params.put("page", page);
        params.put("per_page", perPage);

        RequestContent requestContent = new RequestContent().setUrl(String.format(WjApiConstants.LIST_GROUP, teamId.toString()))
                .setParam(params);
        WjBaseResponse<WjPage<TeamGroup>> response = wjService.doGetWithToken(requestContent, () ->
                JacksonUtil.jsonToObject(requestContent.getResult(), new TypeReference<WjBaseResponse<WjPage<TeamGroup>>>() {
                }));
        return response.getData();
    }
}
