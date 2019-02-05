let url = "http://localhost:4567/movies";

function getApiUrl() {
  return url;
}

String.prototype.replaceAll = function(search, replacement) {
    var target = this;
    return target.replace(new RegExp(search, 'g'), replacement);
};

export function getMovies(searchTerms) {
    const url = getApiUrl() + '?search=' + searchTerms.replaceAll(' ', '+');
  
    return fetch(url, {
      method: "GET"
    }).then(checkForErrors);
  }

  function checkForErrors(response) {
    if (!response.ok) {
        throw Error(response.statusText);
    }
    return response;
  }
  
  
