<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <weather-table
            v-if="weathers && weathers.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:weathers="weathers"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-weathers="getAllWeathers"
             >

            </weather-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import WeatherTable from "@/components/WeatherTable";
import WeatherService from "../services/WeatherService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    WeatherTable,
  },
  data() {
    return {
      weathers: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllWeathers(sortBy='weatherId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await WeatherService.getAllWeathers(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.weathers.length) {
					this.weathers = response.data.weathers;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching weathers:", error);
        }
        
      } catch (error) {
        console.error("Error fetching weather details:", error);
      }
    },
  },
  mounted() {
    this.getAllWeathers();
  },
  created() {
    this.$root.$on('searchQueryForWeathersChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllWeathers();
    })
  }
};
</script>
<style></style>
