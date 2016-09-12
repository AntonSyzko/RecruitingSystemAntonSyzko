
function departmentFree() {
    var department = document.getElementById('department').value;
    var ck_department = /^[a-zA-Zа-яА-Я]{3,25}$/;
    if (!ck_department.test(department.trim())) {
        jQuery("#department").css("background", " rgba(255, 0, 0, 0.17)");
        return false;
    }
    var isFree = false;
    jQuery.ajax({
        url: "/ajax/validate/department/name/?departmentName=" + department + "",
        cache: false,
        async: false,
        dataType: "html",
        success: function (data) {
            if (data.toString() == 'true') {
                jQuery("#department").css("background", "rgba(0, 241, 145, 0.42)");
                isFree = true;
            } else {
                jQuery("#department").css("background", " rgba(255, 0, 0, 0.17)");
                isFree = false;
            }
        }
    });
    return isFree;
}


function contaisLogin() {
    var login = document.getElementById('login').value;
    console.log(login)
    var ck_login = /^[a-zA-Zа-яА-Я]{3,15}$/;
    if (!ck_login.test(login.trim())) {
        jQuery("#login").css("background", " rgba(255, 0, 0, 0.17)");
        return false;
    }

    var isFree = false;
    jQuery.ajax({
        url: "/ajax/validate/editor/login/?login=" + login + "",
        cache: false,
        async: false,
        dataType: "html",
        success: function (data) {
            console.log(data)
            if (data.toString() == 'true') {
                jQuery("#login").css("background", "rgba(0, 241, 145, 0.42)");
                isFree = true;
            } else {
                jQuery("#login").css("background", " rgba(255, 0, 0, 0.17)");
                isFree = false;
            }
        }
    });
    return isFree;

}

function contaisPassword() {
    var password = document.getElementById('password').value;
    var login = document.getElementById('login').value;
    var ck_password = /^[a-zA-Zа-яА-Я-0-9]{3,15}$/;
    if (!ck_password.test(password.trim())) {
        jQuery("#password").css("background", " rgba(255, 0, 0, 0.17)");
        return false;
    }

    var isFree = false;
    jQuery.ajax({
        url: "/ajax/validate/editor/password/?password=" + password + "&login="+login+"",
        cache: false,
        async: false,
        dataType: "html",
        success: function (data) {
            if (data.toString() == 'true') {
                jQuery("#password").css("background", "rgba(0, 241, 145, 0.42)");
                isFree = true;
            } else {
                jQuery("#password").css("background", " rgba(255, 0, 0, 0.17)");
                isFree = false;
            }
        }
    });
    return isFree;
}



function IsNumeric() {
    var salary = document.getElementById('employee_salary').value;

    var RE = /^[0-9]{1,15}\.[0-9]{2}$/;

    if (RE.test(salary)) {
        jQuery("#employee_salary").css("background", "rgba(0, 241, 145, 0.42)");
        return true;
    } else {
        jQuery("#employee_salary").css("background", "rgba(255, 0, 0, 0.17)");
        return false;
    }

}
function validateDate() {
    var recruited_date = document.getElementById('recruited_date').value;
    var birth_date = document.getElementById('birth_date').value;

    if (birth_date == "" || recruited_date == "") {
        jQuery("#birth_date").css("background", " rgba(255, 0, 0, 0.17)");
        jQuery("#recruited_date").css("background", " rgba(255, 0, 0, 0.17)");
        return false;

    } else {
        jQuery("#birth_date").css("background", "rgba(0, 241, 145, 0.42)");
        jQuery("#recruited_date").css("background", "rgba(0, 241, 145, 0.42)");
        return true;
    }
}


function validateFirstName() {
    var first_name = document.getElementById('first_name').value;
    var ck_first_name = /^[a-zA-Zа-яА-Я]{3,15}$/;
    if (!ck_first_name.test(first_name.trim())) {
        jQuery("#first_name").css("background", " rgba(255, 0, 0, 0.17)");
        return false;

    } else {
        jQuery("#first_name").css("background", "rgba(0, 241, 145, 0.42)");
        return true;
    }
}

function validateLastName() {
    var last_name = document.getElementById('last_name').value;
    var ck_last_name = /^[a-zA-Zа-яА-Я]{3,15}$/;
    if (!ck_last_name.test(last_name.trim())) {
        jQuery("#last_name").css("background", " rgba(255, 0, 0, 0.17)");
        return false;
    } else {
        jQuery("#last_name").css("background", "rgba(0, 241, 145, 0.42)");


        return true;
    }
}
function validateDepartment() {
    var department = document.getElementById('departmentId').value;
    if (department == "") {
        jQuery("#departmentId").css("background", " rgba(255, 0, 0, 0.17)");
        return false;
    } else {
        jQuery("#departmentId").css("background", "rgba(0, 241, 145, 0.42)");
        return true;
    }

}



function validatePassword() {
    var password = document.getElementById('user_password').value;
    var ck_password = /^[a-zA-Zа-яА-Я-0-9]{3,15}$/;
    if (!ck_password.test(password.trim())) {
        jQuery("#user_password").css("background", " rgba(255, 0, 0, 0.17)");
        return false;

    } else {
        jQuery("#user_password").css("background", "rgba(0, 241, 145, 0.42)");


        return true;
    }
}

function freeName() {
    var name = document.getElementById('user_name').value;
    var ck_name = /^[a-zA-Zа-яА-Я]{3,15}$/;
    if (!ck_name.test(name.trim())) {
        jQuery("#user_name").css("background", " rgba(255, 0, 0, 0.17)");
        return false;
    }
    var isFree = false;
    jQuery.ajax({
        url: "/ajax/validate/editor/name/?editorName=" + name + "",
        cache: false,
        async: false,
        dataType: "html",
        success: function (data) {
            // name = JSON.parse(list);
            if (data.toString() == 'true') {
                jQuery("#user_name").css("background", "rgba(0, 241, 145, 0.42)");
                isFree = true;
            } else {
                jQuery("#user_name").css("background", " rgba(255, 0, 0, 0.17)");
                isFree = false;
            }
        }
    });
    return isFree;
}
