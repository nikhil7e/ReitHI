function checkIfUserHasLeftComment(username) {
   debugger
   const reviews = document.querySelector('.review');
   console.log("wtf")
   let i = 0;
   for (let review in reviews) {
      console.log(i++)
      if (review.firstChild.innerText == username) {
         review.classList.add('userReview');
         console.log("halló")
      }
   }
}