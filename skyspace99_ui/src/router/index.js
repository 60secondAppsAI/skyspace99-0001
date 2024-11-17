import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import Flights from  '@/pages/Flights.vue';
import FlightDetail from  '@/pages/FlightDetail.vue';
import Aircrafts from  '@/pages/Aircrafts.vue';
import AircraftDetail from  '@/pages/AircraftDetail.vue';
import Airlines from  '@/pages/Airlines.vue';
import AirlineDetail from  '@/pages/AirlineDetail.vue';
import Passengers from  '@/pages/Passengers.vue';
import PassengerDetail from  '@/pages/PassengerDetail.vue';
import Bookings from  '@/pages/Bookings.vue';
import BookingDetail from  '@/pages/BookingDetail.vue';
import Tickets from  '@/pages/Tickets.vue';
import TicketDetail from  '@/pages/TicketDetail.vue';
import Crews from  '@/pages/Crews.vue';
import CrewDetail from  '@/pages/CrewDetail.vue';
import Meals from  '@/pages/Meals.vue';
import MealDetail from  '@/pages/MealDetail.vue';
import Airports from  '@/pages/Airports.vue';
import AirportDetail from  '@/pages/AirportDetail.vue';
import Terminals from  '@/pages/Terminals.vue';
import TerminalDetail from  '@/pages/TerminalDetail.vue';
import Luggages from  '@/pages/Luggages.vue';
import LuggageDetail from  '@/pages/LuggageDetail.vue';
import Gates from  '@/pages/Gates.vue';
import GateDetail from  '@/pages/GateDetail.vue';
import Schedules from  '@/pages/Schedules.vue';
import ScheduleDetail from  '@/pages/ScheduleDetail.vue';
import FrequentFlyers from  '@/pages/FrequentFlyers.vue';
import FrequentFlyerDetail from  '@/pages/FrequentFlyerDetail.vue';
import Promotions from  '@/pages/Promotions.vue';
import PromotionDetail from  '@/pages/PromotionDetail.vue';
import Weathers from  '@/pages/Weathers.vue';
import WeatherDetail from  '@/pages/WeatherDetail.vue';
import Reviews from  '@/pages/Reviews.vue';
import ReviewDetail from  '@/pages/ReviewDetail.vue';
import BaggageClaims from  '@/pages/BaggageClaims.vue';
import BaggageClaimDetail from  '@/pages/BaggageClaimDetail.vue';
import Seats from  '@/pages/Seats.vue';
import SeatDetail from  '@/pages/SeatDetail.vue';
import BoardingPasss from  '@/pages/BoardingPasss.vue';
import BoardingPassDetail from  '@/pages/BoardingPassDetail.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
			redirect: '/flights',
																				  },
  {
    path: "/pricing",
    name: "PricingView",
    component: () => import("../views/PricingView.vue"),
  },
  {
    path: "/arts-gallery",
    name: "ArtsGalleryView",
    component: () => import("../views/ArtsGalleryView.vue"),
  },
  {
    path: "/checkout/:id",
    name: "CheckoutView",
    component: () => import("../views/CheckoutView.vue"),
  },
  {
    path: "/stripe-checkout",
    name: "StripeCheckoutView",
    component: () => import("../views/StripeCheckoutView.vue"),
  },
	{
		path: '/flights',
		name: 'Flights',
		layout: DefaultLayout,
		component: Flights,
	},
	{
	    path: '/flight/:flightId', 
	    name: 'FlightDetail',
		layout: DefaultLayout,
	    component: FlightDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/aircrafts',
		name: 'Aircrafts',
		layout: DefaultLayout,
		component: Aircrafts,
	},
	{
	    path: '/aircraft/:aircraftId', 
	    name: 'AircraftDetail',
		layout: DefaultLayout,
	    component: AircraftDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/airlines',
		name: 'Airlines',
		layout: DefaultLayout,
		component: Airlines,
	},
	{
	    path: '/airline/:airlineId', 
	    name: 'AirlineDetail',
		layout: DefaultLayout,
	    component: AirlineDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/passengers',
		name: 'Passengers',
		layout: DefaultLayout,
		component: Passengers,
	},
	{
	    path: '/passenger/:passengerId', 
	    name: 'PassengerDetail',
		layout: DefaultLayout,
	    component: PassengerDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/bookings',
		name: 'Bookings',
		layout: DefaultLayout,
		component: Bookings,
	},
	{
	    path: '/booking/:bookingId', 
	    name: 'BookingDetail',
		layout: DefaultLayout,
	    component: BookingDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/tickets',
		name: 'Tickets',
		layout: DefaultLayout,
		component: Tickets,
	},
	{
	    path: '/ticket/:ticketId', 
	    name: 'TicketDetail',
		layout: DefaultLayout,
	    component: TicketDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/crews',
		name: 'Crews',
		layout: DefaultLayout,
		component: Crews,
	},
	{
	    path: '/crew/:crewId', 
	    name: 'CrewDetail',
		layout: DefaultLayout,
	    component: CrewDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/meals',
		name: 'Meals',
		layout: DefaultLayout,
		component: Meals,
	},
	{
	    path: '/meal/:mealId', 
	    name: 'MealDetail',
		layout: DefaultLayout,
	    component: MealDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/airports',
		name: 'Airports',
		layout: DefaultLayout,
		component: Airports,
	},
	{
	    path: '/airport/:airportId', 
	    name: 'AirportDetail',
		layout: DefaultLayout,
	    component: AirportDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/terminals',
		name: 'Terminals',
		layout: DefaultLayout,
		component: Terminals,
	},
	{
	    path: '/terminal/:terminalId', 
	    name: 'TerminalDetail',
		layout: DefaultLayout,
	    component: TerminalDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/luggages',
		name: 'Luggages',
		layout: DefaultLayout,
		component: Luggages,
	},
	{
	    path: '/luggage/:luggageId', 
	    name: 'LuggageDetail',
		layout: DefaultLayout,
	    component: LuggageDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/gates',
		name: 'Gates',
		layout: DefaultLayout,
		component: Gates,
	},
	{
	    path: '/gate/:gateId', 
	    name: 'GateDetail',
		layout: DefaultLayout,
	    component: GateDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/schedules',
		name: 'Schedules',
		layout: DefaultLayout,
		component: Schedules,
	},
	{
	    path: '/schedule/:scheduleId', 
	    name: 'ScheduleDetail',
		layout: DefaultLayout,
	    component: ScheduleDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/frequentFlyers',
		name: 'FrequentFlyers',
		layout: DefaultLayout,
		component: FrequentFlyers,
	},
	{
	    path: '/frequentFlyer/:frequentFlyerId', 
	    name: 'FrequentFlyerDetail',
		layout: DefaultLayout,
	    component: FrequentFlyerDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/promotions',
		name: 'Promotions',
		layout: DefaultLayout,
		component: Promotions,
	},
	{
	    path: '/promotion/:promotionId', 
	    name: 'PromotionDetail',
		layout: DefaultLayout,
	    component: PromotionDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/weathers',
		name: 'Weathers',
		layout: DefaultLayout,
		component: Weathers,
	},
	{
	    path: '/weather/:weatherId', 
	    name: 'WeatherDetail',
		layout: DefaultLayout,
	    component: WeatherDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/reviews',
		name: 'Reviews',
		layout: DefaultLayout,
		component: Reviews,
	},
	{
	    path: '/review/:reviewId', 
	    name: 'ReviewDetail',
		layout: DefaultLayout,
	    component: ReviewDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/baggageClaims',
		name: 'BaggageClaims',
		layout: DefaultLayout,
		component: BaggageClaims,
	},
	{
	    path: '/baggageClaim/:baggageClaimId', 
	    name: 'BaggageClaimDetail',
		layout: DefaultLayout,
	    component: BaggageClaimDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/seats',
		name: 'Seats',
		layout: DefaultLayout,
		component: Seats,
	},
	{
	    path: '/seat/:seatId', 
	    name: 'SeatDetail',
		layout: DefaultLayout,
	    component: SeatDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/boardingPasss',
		name: 'BoardingPasss',
		layout: DefaultLayout,
		component: BoardingPasss,
	},
	{
	    path: '/boardingPass/:boardingPassId', 
	    name: 'BoardingPassDetail',
		layout: DefaultLayout,
	    component: BoardingPassDetail,
	    props: true // Pass route params as props to the component
  	},
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes,
});

export default router;
