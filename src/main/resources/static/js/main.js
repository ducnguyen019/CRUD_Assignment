let pageable = {
    page: 0,
    size: 5,
    totalPage: 10
}
$(function () {

    changePage(pageable);

    $('ul.pagination li a').on('click', function (event) {
        event.preventDefault();

        console.log(event);
        let pageable = {
            page: event.target.innerHTML - 1,
            size: 5
        }

        changePage(pageable);
        $(".page-item").removeClass('active')
        $(event.target).parent().addClass('active')

    });

    $("#add-form").submit(function (event) {
        console.log("abc");
        event.preventDefault();
        let $form = $(this);
        let data = getFormData($form);
        console.log(data);
        $.ajax({
            type: 'post',
            url: '/api/v1/group',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            traditional: true,
            success: function (response) {
                console.log(response)
                alert("Thêm thành công");
                changePage(pageable);
                $('#exampleModal').modal('hide');
            }
        });
    });

    $("#update-form").submit(function (event) {
        event.preventDefault();
        let $form = $(this);
        let data = getFormData($form);
        console.log(data);
        $.ajax({
            type: 'put',
            url: '/api/v1/group/' + data.id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            traditional: true,
            success: function (response) {
                console.log(response)
                alert("Update thành công");
                changePage(pageable);
                $('#updateModal').modal('hide');
            }
        });
    });
})

function deleteGroup(groupId) {
    let isOk = confirm("Bạn có chắc chắn xoá group này không?");
    if (isOk) {
        $.ajax({
            url: '/api/v1/group/' + groupId,
            type: 'DELETE',
            success: function (result) {
                alert("Đã xoá thành công")
                changePage(pageable);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown){
                alert(XMLHttpRequest.responseJSON.message);
            }
        });
    }
}

function renderListGroup(data) {
    let html = '';
    data.content.forEach(item => {
        html += `<tr>
            <td>${item.id}</td>
            <td>${item.groupName}</td>
            <td>${item.member}</td>
            <td>${item.creator}</td>
            <td>${item.createDate}</td>
            <td>
                <button class="btn btn-sm btn-success" onclick='updateGroup(${JSON.stringify(item)})'>Update</button> 
                <button class="btn btn-sm btn-danger" onclick="deleteGroup(${item.id})">Xoá</button> 
            </td>
        </tr>`;
    })
    $("#group-data").html(html);
}

function changePage(pageable) {
    $.get('/api/v1/group/get-all', pageable, function (data) {
        renderListGroup(data)
    });
}

function searchGroup() {
    let searchValue = $("#txt-find-groupId").val();
    if (searchValue) {
        $.get('/api/v1/group/' + searchValue, pageable, function (data) {
            renderListGroup(data)
        })
    }
}

function getFormData($form) {
    let unindexed_array = $form.serializeArray();
    let indexed_array = {};

    $.map(unindexed_array, function (n, i) {
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}

function updateGroup(group) {
    $('#updateModal').modal('show');
    $('#update-form input[name="id"]').val(group.id);
    $('#update-form input[name="groupName"]').val(group.groupName);
    $('#update-form input[name="member"]').val(group.member);
    $('#update-form input[name="creator"]').val(group.creator);
    let createdate = new Date(Date.parse(group.createDate));
    console.log(createdate.toISOString().split('T')[0]);
    // const [a,...b] = '2022-11-14T00:55:31.820ZTaaa'.split('T');
    // console.log(a);
    // console.log(b);
    $('#update-form input[name="createDate"]').val(createdate.toISOString().split('T')[0]);


}

