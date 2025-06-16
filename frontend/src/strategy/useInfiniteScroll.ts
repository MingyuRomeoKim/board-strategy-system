import { useCallback, useEffect, useRef, useState } from 'react';
import { Post } from '../types/post';
import axiosInstance from '../api/axiosInstance';

const PAGE_SIZE = 20;

export function useInfiniteScroll() {
    const [posts, setPosts] = useState<Post[]>([]);
    const [cursor, setCursor] = useState<string | null>(null);
    const [loading, setLoading] = useState(false);
    const [isEnd, setIsEnd] = useState(false);

    const observerRef = useRef<IntersectionObserver | null>(null);
    const targetRef = useRef<HTMLDivElement | null>(null);

    const loadMore = useCallback(async () => {
        if (loading || isEnd) return;

        setLoading(true);

        try {
            const url = cursor 
                ? `/posts?strategy=infinite&cursor=${cursor}&size=${PAGE_SIZE}`
                : `/posts?strategy=infinite&size=${PAGE_SIZE}`;

            const res = await axiosInstance.get<Post[]>(url);

            const newPosts = res.data;

            if (newPosts.length < PAGE_SIZE) {
                setIsEnd(true);
            }

            if (newPosts.length > 0) {
                // Get the last post's ID to use as the next cursor
                const lastPost = newPosts[newPosts.length - 1];
                setCursor(lastPost.postId);
            }

            setPosts((prev) => [...prev, ...newPosts]);
        } catch (err) {
            console.error('🚨 게시글 로딩 실패:', err);
        } finally {
            setLoading(false);
        }
    }, [cursor, loading, isEnd]);

    useEffect(() => {
        loadMore(); // 첫 페이지 자동 로딩
    }, []);

    const setTargetRef = useCallback((node: HTMLDivElement | null) => {
        if (observerRef.current) observerRef.current.disconnect();

        if (node) {
            observerRef.current = new IntersectionObserver(
                ([entry]) => {
                    if (entry.isIntersecting) {
                        loadMore();
                    }
                },
                {
                    root: null,
                    rootMargin: '0px 0px 200px 0px',
                    threshold: 0.1,
                }
            );
            observerRef.current.observe(node);
        }

        targetRef.current = node;
    }, [loadMore]);

    return {
        posts,
        targetRef: setTargetRef,
        loading,
        isEnd,
    };
}
