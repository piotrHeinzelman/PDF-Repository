


function addcode() {
    var code = document.getElementById("old_code").innerHTML;
    var x = document.getElementById('form_projectfind');
    x.action = "projectadd";

	document.getElementById('code').value = code;

    x.submit();
    x.action = "projectfind";
    //Location.reload();
}

function show( id ) {
    location.href = '/project/'+id;
}