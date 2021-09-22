function isSelected() {
    // 检查是否有条目被选择
    var ids = $(".ids");
    var flag = false;
    for (var i = 0; i < ids.length; i++) {
        if (ids[i].checked) {
            flag = true;
            break;
        }
    }
    return flag;
};