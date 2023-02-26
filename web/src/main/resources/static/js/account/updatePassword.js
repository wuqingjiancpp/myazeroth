var updateFlag = {originalPassword: false, newPassword:false, repeatNewPassword:false}
$("#form-updatePassword").submit(function () {
    let oldPwd = $("#originalPassword").val();
    let newPwd = $("#newPassword").val();
    let repeatPwd = $("#repeatNewPassword").val();
    if (oldPwd != "" && newPwd != "" && repeatPwd != ""){
        let flag = compareOldAndNewPwd(oldPwd, newPwd);
        if (!flag){
            return false;
        }
        compareNewAndRepeatPwd(newPwd, repeatPwd);
        console.log("check equals")
    }else if (oldPwd == ""){
    updateFlag.originalPassword = false;
    alert("请输入原密码", "danger", $("#originalPasswordAlertPlaceholderId"));
    }else if (newPwd == ""){
    updateFlag.newPassword = false;
    alert("请输入新密码", "danger", $("#newPasswordAlertPlaceholderId"));
    }else if (repeatPwd == ""){
    updateFlag.repeatNewPassword = false;
    alert("请再次输入新密码", "danger", $("#repeatNewPasswordAlertPlaceholderId"));
    }
    return updateFlag.originalPassword && updateFlag.newPassword && updateFlag.repeatNewPassword;
});
function compareNewAndRepeatPwd(newPwd, repeatPwd) {
    if (repeatPwd != newPwd){
        updateFlag.repeatNewPassword = false;
        updateFlag.newPassword = false;
        alert("两次输入的密码不相同", "danger", $("#repeatNewPasswordAlertPlaceholderId"));
    }else{
        updateFlag.repeatNewPassword = true;
        updateFlag.newPassword = true;
        alert("可以使用", "success", $("#repeatNewPasswordAlertPlaceholderId"));
    }
}
function compareOldAndNewPwd(oldPwd, newPwd) {
    if (oldPwd == newPwd){
        updateFlag.originalPassword = false;
        updateFlag.newPassword = false;
        alert("新密码与原密码相同", "danger", $("#newPasswordAlertPlaceholderId"));
        return false;
    }else{
        updateFlag.originalPassword = true;
        updateFlag.newPassword = true;
        alert("可以使用", "success", $("#newPasswordAlertPlaceholderId"));
        return true;
    }
}
var resetFlag = {resetPassword: false, confirmResetPassword:false}
$("#form-resetPassword").submit(function () {
    let resetPwd = $("#resetPassword").val();
    let cfmPwd = $("#confirmResetPassword").val();
    if (resetPwd != "" && cfmPwd != ""){
    compareResetAndCfmPwd(resetPwd, cfmPwd);
    console.log("check equals between")
    }else if (resetPwd == ""){
    alert("请输入重置密码", "danger", $("#resetPasswordAlertPlaceholderId"));
    }else if (cfmPwd == ""){
    alert("请再次输入新密码", "danger", $("#confirmResetPasswordAlertPlaceholderId"));
    }
    return resetFlag.resetPassword && resetFlag.confirmResetPassword;
});
function compareResetAndCfmPwd(resetPwd, cfmPwd) {
    if (cfmPwd != resetPwd){
        resetFlag.resetPassword = false;
        resetFlag.confirmResetPassword = false;
        alert("两次密码输入不相同", "danger", $("#confirmResetPasswordAlertPlaceholderId"));
    }else{
        resetFlag.confirmResetPassword = true;
        resetFlag.resetPassword = true;
        alert("可以使用", "success", $("#confirmResetPasswordAlertPlaceholderId"));
    }
}

const alert = (message, type, selectId) => {
    let svg_href;
    let label_type;
    if (type == "danger"){
        svg_href = "#exclamation-triangle-fill";
        label_type = "Danger";
    }else if (type == "success"){
        svg_href = "#check-circle-fill";
        label_type = "Success";
    }
    const wrapper = document.createElement('div')
    wrapper.innerHTML = [
    `<div class="alert alert-${type} d-flex align-items-center fade show" role="alert">`,
    `<svg class="bi flex-shrink-0 me-2" role="img" aria-label="${label_type}:" style="width: 16px;height: 16px;">`,
    `<use xlink:href="${svg_href}"/>`,
    '</svg>',
    `<div>${message}</div>`,
    '   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>',
    '</div>'
    ].join('')

    selectId.html(wrapper)
}

