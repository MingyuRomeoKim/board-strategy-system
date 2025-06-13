import React from 'react';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import PostListPage from './pages/PostListPage';
import PostDetailPage from './pages/PostDetailPage';
import NotFoundPage from './pages/NotFoundPage';

const App: React.FC = () => {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<PostListPage/>}/>
                <Route path="/posts/:postId" element={<PostDetailPage/>}/>
                <Route path="/404" element={<NotFoundPage/>}/>
                <Route path="*" element={<NotFoundPage/>}/>
            </Routes>
        </Router>
    );
};

export default App;
