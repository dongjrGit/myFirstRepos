var validate = {
    regExp_Number: /^\d+$/,
    regExp_PositiveNumber: /^[0-9]*[1-9][0-9]*$/,
    regExp_Mobile: /^1[3,4,5,7,8][0-9]{9}$/,
    regExp_Chinese: /^[\u0391-\uFFE5]$/,
    regExp_QQ: /^[1-9]\d{4,10}$/,
    regExp_PostCode: /^\d{6}$/,
    regExp_Email: /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
    regExp_Money:/^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/,

    isNotEmpty: function (obj, value, message) {
        if (obj.val() == "" || obj.val().length == 0) {
            //alert(message);
            showWXTS(message);
            obj.val(value).focus();
            return false;
        }
        return true;
    },

    isInRange: function (obj, value, minValue, maxValue, message) {
        if (!minValue && parseInt(obj.val()) > maxValue) {
            showWXTS(message);
            obj.val(value).focus();
            return false;
        }
        else if (!maxValue && parseInt(obj.val()) < minValue) {
            showWXTS(message);
            obj.val(value).focus();
            return false;
        }
        else if ((maxValue && parseInt(obj.val()) > maxValue) || (minValue && parseInt(obj.val()) < minValue)) {
            showWXTS(message);
            obj.val(value).focus();
            return false;
        }
        return true;
    },

    isMatchRegExp: function (obj, value, regExp, message) {
        if (!regExp.test(obj.val())) {
            //alert(message);
            showWXTS(message);
            obj.val(value).focus();
            return false;
        }
        return true;
    },

    isNumber: function (obj, value, message) {
        return this.isMatchRegExp(obj, value, this.regExp_Number, message);
    },

    isPositiveNumber: function (obj, value, message) {
        return this.isMatchRegExp(obj, value, this.regExp_PositiveNumber, message);
    },

    isMobile: function (obj, value, message) {
        return this.isMatchRegExp(obj, value, this.regExp_Mobile, message);
    },

    isChinese: function (obj, value, message) {
        return this.isMatchRegExp(obj, value, this.regExp_Chinese, message);
    },

    isQQ: function (obj, value, message) {
        return this.isMatchRegExp(obj, value, this.regExp_QQ, message);
    },

    isPostCode: function (obj, value, message) {
        return this.isMatchRegExp(obj, value, this.regExp_PostCode, message);
    },
    isEmail: function (obj, value, message) {
        return this.isMatchRegExp(obj, value, this.regExp_Email, message);
    }
};