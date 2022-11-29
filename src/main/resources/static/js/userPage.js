function getPath() {
    var curPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var pos = curPath.indexOf(pathName);
    var localhostPath = curPath.substring(0, pos);
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return localhostPath + projectName;
}

$(document).ready(function () {
    initTable1();
});

$(document).ready(function () {
    initTable2();
});

$(document).ready(function () {
    initTable3();
});

// 表格1搜索
function searchTable1() {
    var keyWord = $("#searchKeyWord").val();
    $('#table1').bootstrapTable('destroy');
    $("#table1").bootstrapTable({
        method: "post",
        url: getPath() + "/user/fuzzSearch",
        cache: false,
        pagination: true, //启动分页
        pageList: "All", //记录数可选列表
        search: false,  //是否启用查询
        showColumns: true,  //显示下拉框勾选要显示的列
        sidePagination: "client", //表示客户端请求
        queryParamsType: "limit",
        clickToSelect: true,
        locale: 'en-US',
        showRefresh: true,
        //checkboxHeader: false,
        //singleSelect: true,
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                limit: params.limit,   //页面大小
                offset: params.offset,  //页码
                search: params.search,
                sort: params.sort,
                order: params.order,
                keyWord: keyWord,
            };
            return param;
        },
        onLoadSuccess: function () {  //加载成功时执行
            $('#searchModal1').modal("hide");
            $('#searchKeyWord').val('');
            $('#mResult1').addClass('alert-success');
            $('#mResult1').html("Success!");
            setTimeout(function () {
                $('#mResult1').removeClass('alert-success');
                $('#mResult1').html('');
            }, 2000);
        },
        onLoadError: function () {  //加载失败时执行
            $('#searchModal1').modal("hide");

            $('#mResult1').addClass('alert-danger');
            $('#mResult1').html("Server Failed!");
            setTimeout(function () {
                $('#mResult1').removeClass('alert-danger');
                $('#mResult1').html('');
            }, 2000);
        },
    });
}



function initTable1() {
    $('#table1').bootstrapTable('destroy');
    $("#table1").bootstrapTable({
        method: "post",
        url: getPath() + "/user/findAllSchedule",
        cache: false,
        pagination: true, //启动分页
        pageList: "All", //记录数可选列表
        search: false,  //是否启用查询
        showColumns: true,  //显示下拉框勾选要显示的列
        sidePagination: "client", //表示客户端请求
        queryParamsType: "limit",
        clickToSelect: true,
        locale: 'en-US',
        showRefresh: true,
        //checkboxHeader: false,
        //singleSelect: true,
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                limit: params.limit,   //页面大小
                offset: params.offset,  //页码
                search: params.search,
                sort: params.sort,
                order: params.order,
            };
            return param;
        },
        onLoadSuccess: function () {  //加载成功时执行
            $('#mResult1').addClass('alert-success');
            $('#mResult1').html("Success!");
            setTimeout(function () {
                $('#mResult1').removeClass('alert-success');
                $('#mResult1').html('');
            }, 2000);
        },
        onLoadError: function () {  //加载失败时执行
            $('#mResult1').addClass('alert-danger');
            $('#mResult1').html("Server Failed!");
            setTimeout(function () {
                $('#mResult1').removeClass('alert-danger');
                $('#mResult1').html('');
            }, 2000);
        },
    });
}





// 搜索保存刷新表1
$('#searchSave1').click(function () {
    searchTable1();
});

//initTable2
function initTable2() {
    $('#table2').bootstrapTable('destroy');
    $("#table2").bootstrapTable({
        method: "get",
        url: getPath() + "/currentUserRecord",
        cache: false,
        pagination: true, //启动分页
        pageList: "All", //记录数可选列表
        search: false,  //是否启用查询
        showColumns: true,  //显示下拉框勾选要显示的列
        sidePagination: "client", //表示服务端请求
        queryParamsType: "limit",
        clickToSelect: true,
        locale: 'zh-CN',
        //checkboxHeader: false,
        //singleSelect: true,
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                limit: params.limit,   //页面大小
                offset: params.offset,  //页码
                search: params.search,
                sort: params.sort,
                order: params.order,
            };
            return param;
        },
        onLoadSuccess: function () {  //加载成功时执行
            $('#mResult2').addClass('alert-success');
            $('#mResult2').html("Success!");
            setTimeout(function () {
                $('#mResult2').removeClass('alert-success');
                $('#mResult2').html('');
            }, 2000);
        },
        onLoadError: function () {  //加载失败时执行
            $('#mResult2').addClass('alert-danger');
            $('#mResult2').html("Server Failed!");
            setTimeout(function () {
                $('#mResult2').removeClass('alert-danger');
                $('#mResult2').html('');
            }, 2000);
        },
    });
}

// 表格2搜索
function searchTable2() {
    var keyWord = $("#fuzzSearch2").val();
    // var gymName = $("#searchGymName2").val();
    // var visitDate = $("#searchVisitDate2").val();
    $('#table2').bootstrapTable('destroy');
    $("#table2").bootstrapTable({
        method: "post",
        url: getPath() + "/user/fuzzSearch2",
        cache: false,
        pagination: true, //启动分页
        pageList: "All", //记录数可选列表
        search: false,  //是否启用查询
        showColumns: true,  //显示下拉框勾选要显示的列
        sidePagination: "client", //表示服务端请求
        queryParamsType: "limit",
        clickToSelect: true,
        locale: 'en-US',
        showRefresh: true,
        //checkboxHeader: false,
        //singleSelect: true,
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                limit: params.limit,   //页面大小
                offset: params.offset,  //页码
                search: params.search,
                sort: params.sort,
                order: params.order,
                keyWord: keyWord,
            };
            return param;
        },
        onLoadSuccess: function () {  //加载成功时执行
            $('#searchModal2').modal("hide");
            $('#fuzzSearch2').val('');
            $('#mResult2').addClass('alert-success');
            $('#mResult2').html("Success!");
            setTimeout(function () {
                $('#mResult2').removeClass('alert-success');
                $('#mResult2').html('');
            }, 2000);
        },
        onLoadError: function () {  //加载失败时执行
            $('#searchModal2').modal("hide");
            $('#mResult2').addClass('alert-danger');
            $('#mResult2').html("Server Failed!");
            setTimeout(function () {
                $('#mResult2').removeClass('alert-danger');
                $('#mResult2').html('');
            }, 2000);
        },
    });
}

//delete
$('#deleteBtn1').click(function () {
    var rows = $('#table2').bootstrapTable('getSelections');
    if (rows.length !== 1) {
        bootbox.alert({
            centerVertical: true,
            title: "Error",
            message: "Allow selecting only 1 row! ",
            locale: "en_US"
        });
    } else {
        //$('#updateSuId').val(rows[0]["suId"]);
        //$('#updateSuUserName').val(rows[0]["suUserName"]);
        //$('#updateActivatedStatus').val(rows[0]["activatedStatus"]);
        $('#deleteModal1').modal("toggle");
    }
})

$('#deleteSave1').click(function () {
    var rows = $('#table2').bootstrapTable('getSelections');
    var deleteId = rows[0]["id"];

    $.ajax({
        type: "post",
        url: getPath() + "/user/deleteSchedule",
        async: false,
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            "id": deleteId,
        }),
        success: function (data) {
            if (data.flag) {
                $('#deleteModal1').modal("hide");
                bootbox.alert({
                    centerVertical: true,
                    title: "Success",
                    message: "Success!",
                    locale: "en-US"
                })
            } else {
                $('#deleteModal1').modal("hide");
                bootbox.alert({
                    centerVertical: true,
                    title: "Failed",
                    message: "Failed!",
                    locale: "en-US"
                });
            }

        },
        error: function () {
            $('#deleteModal1').modal("hide");
            $('#mResult2').addClass('alert-danger');
            $('#mResult2').html("Server Failed!");
            setTimeout(function () {
                $('#mResult2').removeClass('alert-danger');
                $('#mResult2').html('');
            }, 200);
        }
    })
    initTable2();
    initTable1();
});


// 刷新Record表格
$('#searchSave2').click(function () {
    searchTable2();
});

// 插入Schedule保存
$('#insertSave1').click(function () {
    var gymName = $('#insertGymName').val();
    var date = $('#insertDate').val();
    var visitTime = $('#visitTime').val();

    $.ajax({
        type: "post",
        url: getPath() + "/bookVenue",
        async: false,
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            "gymName": gymName,
            "date": date,
            "visitTime": visitTime,

        }),
        success: function (data, xhr) {
            if (data.flag) {
                $('#insertModal1').modal("hide");
                $('#insertGymName').val('');
                $('#insertDate').val('');
                $('#visitTime').val('');
                initTable2();
                bootbox.alert({
                    centerVertical: true,
                    title: "Success",
                    message: "Success!",
                    locale: "en_US"
                })
            } else {
                $('#insertModal1').modal("hide");
                bootbox.alert({
                    centerVertical: true,
                    title: "Failed",
                    message: "The time is not available!",
                    locale: "en_US"
                })
            }
        },
        error: function () {
            $('#insertModal1').modal("hide");
            $('#mResult1').addClass('alert-danger');
            $('#mResult1').html("Server Failed!");
            setTimeout(function () {
                $('#mResult1').removeClass('alert-danger');
                $('#mResult1').html('');
            }, 2000);
        }
    })
});


// 更新Schedule信息
$('#updateBtn1').click(function () {
    var rows = $('#table1').bootstrapTable('getSelections');
    if (rows.length !== 1) {
        bootbox.alert({
            centerVertical: true,
            title: "Error",
            message: "Allow selecting only 1 row! ",
            locale: "en_US"
        });
    } else {
        $('#updateUnitPrice').val(rows[0]["unitPrice"]);
        $('#updateGymStatus').val(rows[0]["gymStatus"]);
        $('#updateModal1').modal("toggle");
    }
});

// 更新Schedule保存
$('#updateSave1').click(function () {
    var rows = $('#table1').bootstrapTable('getSelections');
    var updateGymName = rows[0]["gymName"];
    var updateDate = rows[0]["date"]
    var updateUnitPrice = $('#updateUnitPrice').val();
    var updateGymStatus = $('#updateGymStatus').val();


    $.ajax({
        type: "post",
        url: getPath() + "/admin/updateSchedule",
        async: false,
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            "gymName": updateGymName,
            "unitPrice": updateUnitPrice,
            "gymStatus": updateGymStatus,
            "date": updateDate
        }),
        success: function (data) {
            if (data.flag) {
                $('#updateModal1').modal("hide");
                $('#updateUnitPrice').val('');
                $('#updateGymStatus').val('');
                bootbox.alert({
                    centerVertical: true,
                    title: "Success",
                    message: "Success!",
                    locale: "en-US"
                })
            } else {
                $('#updateModal1').modal("hide");
                bootbox.alert({
                    centerVertical: true,
                    title: "Failed",
                    message: "Failed!",
                    locale: "en-US"
                });
            }
        },
        error: function () {
            $('#updateModal1').modal("hide");
            $('#mResult1').addClass('alert-danger');
            $('#mResult1').html("Server Failed!");
            setTimeout(function () {
                $('#mResult1').removeClass('alert-danger');
                $('#mResult1').html('');
            }, 200);
        }
    })
});

// // 删除
// $('#deleteBtn1').click(function () {
//     var rows = $('#table1').bootstrapTable('getSelections');
//     if (rows.length !== 1) {
//         bootbox.alert({
//             centerVertical: true,
//             title: "Error",
//             message: "Allow selecting only 1 row! ",
//             locale: "en_US"
//         });
//     } else {
//         //$('#updateSuId').val(rows[0]["suId"]);
//         //$('#updateSuUserName').val(rows[0]["suUserName"]);
//         //$('#updateActivatedStatus').val(rows[0]["activatedStatus"]);
//         $('#deleteModal1').modal("toggle");
//     }
// })

// // 删除保存
// $('#deleteSave1').click(function () {
//     var rows = $('#table1').bootstrapTable('getSelections');
//     var deleteSuId = rows[0]["suId"];
//
//     $.ajax({
//         type: "post",
//         url: getPath() + "/superAdmin/deleteAccount",
//         async: false,
//         dataType: 'json',
//         contentType: 'application/json',
//         data: JSON.stringify({
//             "suId": deleteSuId,
//         }),
//         success: function (data) {
//             if (data.flag) {
//                 $('#deleteModal1').modal("hide");
//                 bootbox.alert({
//                     centerVertical: true,
//                     title: "Success",
//                     message: "Success!",
//                     locale: "en-US"
//                 })
//             } else {
//                 $('#deleteModal1').modal("hide");
//                 bootbox.alert({
//                     centerVertical: true,
//                     title: "Failed",
//                     message: "Failed!",
//                     locale: "en-US"
//                 });
//             }
//         },
//         error: function () {
//             $('#deleteModal1').modal("hide");
//             $('#mResult1').addClass('alert-danger');
//             $('#mResult1').html("Server Failed!");
//             setTimeout(function () {
//                 $('#mResult1').removeClass('alert-danger');
//                 $('#mResult1').html('');
//             }, 200);
//         }
//     })
// });

// 加载Admin表格3
function initTable3() {
    $('#table3').bootstrapTable('destroy');
    $("#table3").bootstrapTable({
        method: "get",
        url: getPath() + "/currentUser",
        cache: false,
        pagination: true, //启动分页
        pageList: "All", //记录数可选列表
        search: false,  //是否启用查询
        showColumns: true,  //显示下拉框勾选要显示的列
        sidePagination: "client", //表示服务端请求
        queryParamsType: "limit",
        clickToSelect: true,
        locale: 'en-US',
        //checkboxHeader: false,
        //singleSelect: true,
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                limit: params.limit,   //页面大小
                offset: params.offset,  //页码
                search: params.search,
                sort: params.sort,
                order: params.order,
            };
            return param;
        },
        onLoadSuccess: function () {  //加载成功时执行
            $('#mResult3').addClass('alert-success');
            $('#mResult3').html("Success!");
            setTimeout(function () {
                $('#mResult3').removeClass('alert-success');
                $('#mResult3').html('');
            }, 2000);
        },
        onLoadError: function () {  //加载失败时执行
            $('#mResult3').addClass('alert-danger');
            $('#mResult3').html("Server Failed!");
            setTimeout(function () {
                $('#mResult3').removeClass('alert-danger');
                $('#mResult3').html('');
            }, 2000);
        },
    });
}

// 刷新User表格3
$('#refreshBtn3').click(function () {
    initTable3();
});

// 更新User信息
$('#updateBtn3').click(function () {
    var rows = $('#table3').bootstrapTable('getSelections');
    if (rows.length !== 1) {
        bootbox.alert({
            centerVertical: true,
            title: "Error",
            message: "Allow selecting only 1 row! ",
            locale: "en_US"
        });
    } else {
        $('#updateUserPwd').val(rows[0]["userPwd"]);
        $('#updateAge').val(rows[0]["Age"]);
        $('#updateUserPhone').val(rows[0]["userPhone"]);
        $('#updateUserEmail').val(rows[0]["userEmail"]);
        $('#updateModal3').modal("toggle");
    }
});

// 更新Schedule保存
$('#updateSave3').click(function () {
    var rows = $('#table3').bootstrapTable('getSelections');
    var updateUserId = rows[0]["userId"];
    var updateUserPwd = $('#updateUserPwd').val();
    var updateAge = $('#updateAge').val();
    var updateUserPhone = $('#updateUserPhone').val();
    var updateUserEmail = $('#updateUserEmail').val();


    $.ajax({
        type: "post",
        url: getPath() + "/user/updateUserMyself",
        async: false,
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            "userId": updateUserId,
            "userPwd": updateUserPwd,
            "Age": updateAge,
            "userPhone": updateUserPhone,
            "userEmail": updateUserEmail,
        }),
        success: function (data) {
            if (data.flag) {
                $('#updateModal3').modal("hide");
                $('#updateUserPwd').val('');
                $('#updateAge').val('');
                $('#updateUserPhone').val('');
                $('#updateUserEmail').val('');
                bootbox.alert({
                    centerVertical: true,
                    title: "Success",
                    message: "Success!",
                    locale: "en-US"
                })
            } else {
                $('#updateModal3').modal("hide");
                bootbox.alert({
                    centerVertical: true,
                    title: "Failed",
                    message: "Failed!",
                    locale: "en-US"
                });
            }
        },
        error: function () {
            $('#updateModal3').modal("hide");
            $('#mResult3').addClass('alert-danger');
            $('#mResult3').html("Server Failed!");
            setTimeout(function () {
                $('#mResult3').removeClass('alert-danger');
                $('#mResult3').html('');
            }, 200);
        }
    })
    initTable3();
});

