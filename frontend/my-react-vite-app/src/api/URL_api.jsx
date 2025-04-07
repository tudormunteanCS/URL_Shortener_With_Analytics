import axios from 'axios';
const URL = 'http://localhost:9090';
const cloudURL = 'https://19anv1niuf.execute-api.eu-north-1.amazonaws.com/default/retrieveDashboardAnalytics';

const createShortURL = async (longURL) => {
    try {
        const response = await axios.post(URL + '/api/shorten', {
            url: longURL
        });
        return response.data;
    } catch (error) {
        console.error('Error creating short URL:', error);
        throw error;
    }
};

const getAnalytics = async (shortURL) => {
    try {
        const response = await axios.get(`${cloudURL}?shortUrl=${shortURL}`); // here should be lamda call
        console.log('Response:', response.data);
        return response.data;
    } catch (error) {
        console.error('Error getting analytics:', error);
        throw error;
    }
}

const storeAnalytics = async (shortUrl, clickCount, lat, lng) => {
    try {
        const response = await axios.post(URL + '/anayltics', {
            shortUrl: shortUrl,
            clickCount: clickCount,
            lat: lat,
            lng: lng
        });
        return response.data;
    } catch (error) {
        console.error('Error storing analytics:', error);
        throw error;
    }
}

export { createShortURL, getAnalytics, storeAnalytics };
