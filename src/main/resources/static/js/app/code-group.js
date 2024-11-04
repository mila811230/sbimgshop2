$(document).ready(function() {
    $("#codeGroupListId").click(function () {
        console.log("codeGroupList")
    })

    $("#codeGroupReadId").click(function () {
        console.log("codeGroupRead")
    })

    $("#codeGroupRegisterId").click(function () {
		const codeGroupObj = {
			groupCode : $(groupCodeId).val(),
			groupName : $(groupCodeId).val(),
		};
        console.log(JSON.stringify(codeGroupObj));
		
		fetch("/codeGroups", {
			method : "POST",
			headers : {
				"Content-Type" : "application/Json",
			},
			body : JSON.stringify(codeGroupObj)
    	})
		.then(response => response.jason())
		.then(data => console.log(data))
		.catch(error => console.error("error, error"))
	})

    $("#codeGroupDeleteId").click(function () {
        console.log("codeGroupDelete")
    })

    $("#codeGroupModifyId").click(function () {
        console.log("codeGroupModify")
    })

    $("#codeGroupResetId").click(function () {
        console.log("codeGroupReset")
    })
});