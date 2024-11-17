import http from "../http-common"; 

class WeatherService {
  getAllWeathers(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/weather/weathers`, searchDTO);
  }

  get(weatherId) {
    return this.getRequest(`/weather/${weatherId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/weather?field=${matchData}`, null);
  }

  addWeather(data) {
    return http.post("/weather/addWeather", data);
  }

  update(data) {
  	return http.post("/weather/updateWeather", data);
  }
  
  uploadImage(data,weatherId) {
  	return http.postForm("/weather/uploadImage/"+weatherId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new WeatherService();
