var flag = {email:false, password:false};

$("#form-sign-up").submit(function (){
    let email = $("#email").val();
    let password = $("#password").val();
    if (email != "" && password != ""){
        checkEmail(email);
        flag.password = true;
    }else if (email == ""){
        flag.email =false;
        console.log("email不能为空")
        alert("Email不能为空", "danger");
    }else if (password == ""){
        console.log("pwd不能为空")
        flag.password = false;
        alert("密码不能为空", "danger");
    }
    return flag.email && flag.password;
});

function checkEmail(email){
    let emailPattern = new RegExp(/([\w\-]+\@[\w\-]+\.[\w\-]+)/);
    console.log(emailPattern.test(email));
    if (emailPattern.test(email)){
        $("#email").blur();
    }else{
        flag.email = false;
        alert("Email格式错误", "danger");
    }
}

$("#email").blur(function () {
    let email = this.value;
    if (email != ""){
        console.log("ajax校验email="+email)
        $.get( "index/checkEmailExists/"+email, function( data ) {
            console.log("data="+data)
            if (data == "ok"){
                if (!flag.email){
                    alert("Email可以使用", "success");
                    flag.email = true;
                }
            }else{
                flag.email = false;
                alert("Email已经被使用", "danger");
            }
        });
    }
});


const alertPlaceholder = document.getElementById('liveAlertPlaceholder')

const alert = (message, type) => {
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
        `<div class="alert alert-${type} alert-dismissible d-flex align-items-center fade show" role="alert">`,
        `<svg class="bi flex-shrink-0 me-2" role="img" aria-label="${label_type}:" style="width: 16px;height: 16px;">`,
        `<use xlink:href="${svg_href}"/>`,
        '</svg>',
        `<div>${message}</div>`,
        '   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>',
        '</div>'
    ].join('')

    alertPlaceholder.append(wrapper)
}

