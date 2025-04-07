import { useState } from 'react'
import './App.css'
import { createShortURL, getAnalytics, storeAnalytics } from './api/URL_api'

function App() {

  const [url,setUrl] = useState('')
  const [shortURL,setShortURL] = useState('')
  const [showDashboard,setShowDashboard] = useState(false)
  const [clickCount,setClickCount] = useState(0)
  const [latitude,setLatitude] = useState('')
  const [longitude,setLongitude] = useState('')
  const [dashboardData,setDashBoardData] = useState(null)

  const handleShortenLink = () => {
    setClickCount(0)
    console.log('Shorten link:',url)
    createShortURL(url)
      .then((shortURL) => {
        console.log('Short URL:', shortURL)
        setShortURL(shortURL)
  })
      .catch((error) => console.error('Error creating short URL:', error))
  }

  const handleShowDashboard = () => {
    setClickCount(clickCount + 1)
    //retrieve lat and long from the browser
    navigator.geolocation.getCurrentPosition((position) => {
      setLatitude(position.coords.latitude)
      setLongitude(position.coords.longitude)
      console.log('Latitude:',latitude)
      console.log('Longitude:',longitude)
    })
    if(latitude != '' && longitude != '')
      {
        storeAnalytics(shortURL,clickCount,latitude,longitude)
          .then((data) => {
            console.log('Stored analytics:', data)
          })
          .catch((error) => console.error('Error storing analytics:', error))
    }


    // console.log('triggering event(lambda): retriving dashboard analytics for the short URL:',shortURL)
    getAnalytics(shortURL)
      .then((data) => {
        setDashBoardData(data)
        setShowDashboard(true)
      })
      .catch((error) => console.error('Error retrieving analytics:', error))
    
  }
  return (
    <>
      <h1>URL Shortener</h1>
      <input type="text" 
      placeholder="Enter URL"
      value={url}
      onChange={(e) => setUrl(e.target.value)}
      />
      <button onClick={handleShortenLink}>Shorten</button>
      {shortURL && (
        <p>Shortened URL: <a onClick={handleShowDashboard} href={shortURL} target="_blank" rel="noopener noreferrer">{shortURL}</a></p>
      )}
      {
        showDashboard && (
          <div>
            <h2>Dashboard</h2>
            <p>Analytics for the short URL: {shortURL}</p>
            {/* Display analytics here retrived from the api*/}
          </div>
        )
      }
    </>
  )
}

export default App
