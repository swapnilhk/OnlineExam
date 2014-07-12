function validateForEmpty(formName, formElements, formElementDisplayNames){
	var index;
	for (index = 0; index < formElements.length; ++index) {
		var x = document.forms[formName][formElements[index]].value;
	    if (x == null || x == "") {
	        alert(formElementDisplayNames[index].concat(" name must be filled out"));
	        return false;
	    }
	}
	return true;
}

function validationForNumeric(formName, formElements, formElementDisplayNames){
	var index;
	for (index = 0; index < formElements.length; ++index) {
		var x = document.forms[formName][formElements[index]].value;
	    if (isNaN(x)) {
	        alert(formElementDisplayNames[index].concat(" should be numeric"));
	        return false;
	    }
	}
	return true;
}

function validationForLength(formName, formElements, formElementDisplayNames){
	var index;
	for (index = 0; index < formElements.length; ++index) {
		var x = document.forms[formName][formElements[index]].value;
	    if (x.length < 8) {
	        alert(formElementDisplayNames[index].concat(" should be at least 8 characters long"));
	        return false;
	    }
	}
	return true;
}

function validateAddQuestionForm() {	
	var formElements = ["question", "option1", "option2", "option3", "option4", "marks"];
	var formElementDisplayNames = ["Question", "Option A", "Option B", "Option C", "Option D", "Marks"];
	if(validateForEmpty("addQuestion", formElements, formElementDisplayNames)){
		formElements = ["marks"];
		formElementDisplayNames = ["Marks"];
		return validationForNumeric("addQuestion", formElements, formElementDisplayNames);
	}
	else return false;	
}

function validateAddStudentForm() {	
	var formElements = ["name", "username", "password"];
	var formElementDisplayNames = ["Name", "Usrname", "Password"];
	if(validateForEmpty("addStudent", formElements, formElementDisplayNames)){
		return validationForLength("addStudent", formElements, formElementDisplayNames);
	}
	else return false;	
}

function validateStudentLogin(){
	var formElements = ["username", "password"];
	var formElementDisplayNames = ["Usrname", "Password"];
	return validateForEmpty("studentLogin", formElements, formElementDisplayNames);
}

function validateAdminLogin(){
	var formElements = ["username", "password"];
	var formElementDisplayNames = ["Usrname", "Password"];
	return validateForEmpty("adminLogin", formElements, formElementDisplayNames);
}
