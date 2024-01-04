$('.visibility-cart').on('click',function(){

  var $btn =  $(this);
  var $cart = $('.cart');
  console.log($btn);

  if ($btn.hasClass('is-open')) {
    $btn.removeClass('is-open');
    $btn.text('O')
    $cart.removeClass('is-open');
    $cart.addClass('is-closed');
    $btn.addClass('is-closed');
  } else {
    $btn.addClass('is-open');
    $btn.text('X')
    $cart.addClass('is-open');
    $cart.removeClass('is-closed');
    $btn.removeClass('is-closed');
  }


});;