var menuPopup = document.querySelector('.popup')
var menuTriangle = document.querySelector('.triangle')
document.querySelector('.globe').addEventListener('click', function () {
  menuPopup.classList.toggle('visible')
  menuTriangle.classList.toggle('visible')
  // secret message
  console.log('Yes, salmon is a color!')
}, false)