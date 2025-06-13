import React from 'react';
import { Card, CardContent, Typography } from '@mui/material';
import { Post } from '../types/post';
import { useNavigate } from 'react-router-dom';
import dayjs from 'dayjs';

interface PostCardProps {
    post: Post;
}

const PostCard: React.FC<PostCardProps> = ({ post }) => {
    const navigate = useNavigate();

    const handleTitleClick = () => {
        navigate(`/posts/${post.postId}`);
    };

    return (
        <Card sx={{ marginBottom: 2 }}>
            <CardContent>
                <Typography variant="h6" gutterBottom onClick={handleTitleClick} sx={{ cursor: 'pointer', color: 'blue' }}>
                    {post.title}
                </Typography>
                <Typography variant="body2" color="text.secondary" paragraph>
                    {post.content}
                </Typography>
                <Typography variant="caption" color="text.secondary">
                    작성자: {post.author} | 작성일: {dayjs(post.createdAt).format('YYYY-MM-DD HH:mm')}
                </Typography>
            </CardContent>
        </Card>
    );
};

export default PostCard;
