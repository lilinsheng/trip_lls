var percent = 0

function boxshake() {
    $('.top').addClass('shake')
    $('.bottom').addClass('shake')
}

function boxOpen() {
    $('.hand.left').addClass("handLeftAnimation")
    $('.top').removeClass('shake')
    $('.top').addClass("topOpenAnimation")
}

function TextChange() {
    $(".ghostText").text("Trick or Treat !")
}

var timer = setInterval(function () {
    $(".bar").css("width", percent + "%")
    percent += 1
    if (percent >= 101) {
        $(".pageLoading").addClass("complete")
        clearInterval(timer)
        setTimeout(boxshake, 500)
        setTimeout(boxOpen, 3000)
        setTimeout(TextChange, 4000)
        setTimeout(skip, 8000)
    }

}, 30)


function skip() {
    window.location.href = "index1.html"
}
